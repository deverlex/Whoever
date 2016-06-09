package vn.whoever.mainserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.whoever.mainserver.model.Languages;
import vn.whoever.mainserver.model.Profiles;
import vn.whoever.mainserver.model.Tokens;
import vn.whoever.mainserver.model.Users;
import vn.whoever.mainserver.service.AuthToken;
import vn.whoever.mainserver.service.LanguagesService;
import vn.whoever.mainserver.service.LocationIPService;
import vn.whoever.mainserver.service.ProfilesService;
import vn.whoever.mainserver.service.UsersService;
import vn.whoever.mainserver.service.utils.ClientLocation;
import vn.whoever.support.model.request.CallLogin;
import vn.whoever.support.model.request.CallRegister;
import vn.whoever.support.model.utils.States;
import vn.whoever.support.response.ReturnCallLogin;
import vn.whoever.support.utils.CalendarFormat;

/**
 * @author Nguyen Van Do
 *	This class describe some function: login with account, login use anonymous mode,
 *	register new account, reconnect from client, check register condition, user logout system
 */

@Controller
@RequestMapping("/mobile/users")
public class MobileUserController {

	@Autowired
	private LanguagesService langsService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private AuthToken authToken;

	@Autowired
	private ProfilesService profileService;

	@Autowired
	private LocationIPService locationService;

	/**
	 * This method for login function Users send info contain: username and
	 * password to web service
	 * 
	 * This sending message by user is described as below { "ssoId" : "",
	 * "password" : "" } This message responsive as below { "avatarPhoto" : "",
	 * "coverPhoto" : "", "nickName" : "", "langName" : "", "birthday" : "",
	 * "gender" : "", "mobile" : "", "email" : "", "isOnline" : "", "privacy" :
	 * "" }
	 */
	@RequestMapping(value = {
			"/login" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody ReturnCallLogin loginWithAccount(HttpServletRequest request, HttpServletResponse response,
			@RequestBody CallLogin login) {

		ReturnCallLogin rCLogin = null;
		HttpSession session = request.getSession();
		try {
			usersService.authenticalUser(request, session, login.getSsoId(), login.getPassword());

			Users user = usersService.findBySsoId(login.getSsoId());
			System.out.println("user login: " + user);
			Profiles profile = profileService.getProfile(user.getIdUser());
			String langName = langsService.findNativeNameById(user.getIdLanguage());

			String avatarPhoto = "null";
			String coverPhoto = "null";

			rCLogin = new ReturnCallLogin();
			rCLogin.setAvatarPhoto(avatarPhoto);
			rCLogin.setCoverPhoto(coverPhoto);
			rCLogin.setNickName(profile.getNickName());
			rCLogin.setLangName(langName);
			rCLogin.setGender(profile.getGenders());
			rCLogin.setBirthday((new CalendarFormat(profile.getBirthday())).getStrDate());
			rCLogin.setEmail(profile.getEmail());
			rCLogin.setMobile(profile.getMobile());
			rCLogin.setIsOnline(user.getIsOnline());
			rCLogin.setPrivacy(profile.getPrivacy());

			// set token is security basic for system
			Tokens tokens = authToken.getToken(login.getSsoId());
			response.setHeader("Whoever-token", tokens.getToken());
			response.setHeader("Token-expiration", tokens.getTimeExp());

			response.setStatus(HttpServletResponse.SC_OK);
			usersService.updateTimeUp(user.getIdUser());
		} catch (Exception e) {
			System.out.println("return status: " + response.getStatus());
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}
		return rCLogin;
	}

	/**
	 * This method provide login function with anonymous mode
	 */
	@RequestMapping(value = { "/anonymous/{langCode}" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String loginAnonymous(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("langCode") String langCode) {

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}

		// get ip => country => language
		ClientLocation location = locationService.getLocation(ipAddress);
		Languages language = langsService.findByCode(langCode);

		if (language == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		// random generate some info
		String ssoId = usersService.generateSsoId();
		String password = usersService.generatePassword();
		String idUser = usersService.generateUserId();

		HttpSession session = request.getSession();
		try {

			Users users = new Users(idUser, ssoId, password, States.active, location.getLatitude(),
					location.getLongitude(), true, true, language.getIdLanguage());

			usersService.registerUser(users);
			usersService.authenticalUser(request, session, ssoId, password);
			//  generating radom token for user
			String token = authToken.initToken(users);
			String date = authToken.getTimeExpiration();
			response.setHeader("Whoever-token", token);
			response.setHeader("Token-expiration", date);

			response.setStatus(HttpServletResponse.SC_CREATED);
		} catch (Exception e) {
			//send error when have a exception
			response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
			return null;
		}
		return language.getNativeName();
	}

	/**
	 * This method for register new user function Message is described as below
	 * {"ssoId" : "", "password" : "", "nickName" : "", "birthday" : "",
	 * "langCode" : ""}
	 */
	@RequestMapping(value = {"/register"}, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody String registerAccount(HttpServletRequest request, HttpServletResponse response,
			@RequestBody CallRegister req) {

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		ClientLocation location = locationService.getLocation(ipAddress);

		Languages languages = langsService.findByCode(req.getLangCode());
		if (languages == null) {
			languages = langsService.findByCode("vi");
		}
		// as login anonymous
		String idUser = usersService.generateUserId();
		HttpSession session = request.getSession();
		String idProfile = profileService.generateIdProfile();

		Users users = null;
		if (location == null) {
			users = new Users(idUser, req.getSsoId(), req.getPassword(), States.active, false, true,
					languages.getIdLanguage());
		} else {
			users = new Users(idUser, req.getSsoId(), req.getPassword(), States.active, location.getLatitude(),
					location.getLongitude(), false, true, languages.getIdLanguage());
		}
		// save a some info of user
		Profiles profile = new Profiles(idProfile, idUser, req.getNickName(), req.getBirthday());

		try {
			usersService.registerUser(users);
			usersService.authenticalUser(request, session, req.getSsoId(), req.getPassword());

			profileService.setProfile(profile);
			// provide a random token 
			String token = authToken.initToken(users);
			String date = authToken.getTimeExpiration();
				
			// set into header http packet
			response.setHeader("Whoever-token", token);
			response.setHeader("Token-expiration", date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return languages.getNativeName();
	}
	
	// try reconnect using tokenId
	@RequestMapping(value = { "/reconnect" }, method = RequestMethod.GET)
	public @ResponseBody Integer reConnect(HttpServletRequest request) {
		try {
			usersService.authByRequest(request);
		} catch (Exception e) {
			return 401;
		}
		return 200;
	}

	/**
	 * Query a account for checking codition create/register a account
	 */
	@RequestMapping(value = { "/query" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String findSsoIdAvaiable(HttpServletResponse response,
			@RequestParam(value = "ssoId", required = true) String ssoId) {
		String idUser = usersService.findIdUser(ssoId);
		if (idUser == null) {
			return "unavaiable";
		}
		System.out.println("find users: " + idUser);
		return "avaiable";
	}
	
	// This function isn't complete
	@RequestMapping(value = { "/get/term" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String acceptTermWhoever(@RequestParam(value = "lang", defaultValue = "en") String langCode) {

		return "";
	}

	// logout system -> destroy session connect of client/user
	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public @ResponseBody void logoutWhoever(HttpSession session,
			@RequestParam(value = "ssoId", required = true) String ssoId) {
		session.invalidate();
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
}
