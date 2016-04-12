package vn.whoever.mainserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.whoever.mainserver.model.Languages;
import vn.whoever.mainserver.model.Tokens;
import vn.whoever.mainserver.model.Users;
import vn.whoever.mainserver.service.AuthenticalToken;
import vn.whoever.mainserver.service.LanguagesService;
import vn.whoever.mainserver.service.UsersService;
import vn.whoever.support.model.request.CallLogin;
import vn.whoever.support.model.request.CallRegister;
import vn.whoever.support.model.utils.States;

@Controller
public class MobileUserController {

	@Autowired
	@Qualifier("whoeverAuthenticationManager")
	protected AuthenticationManager authManager;

	@Autowired
	private LanguagesService langsService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private AuthenticalToken authToken;

	// @Autowired
	// private ProfilesService profileService;

	@RequestMapping(value = {"/mobile/login" }, method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
	public @ResponseBody String loginWithAccount(HttpServletRequest request, HttpServletResponse response,
			@RequestBody CallLogin login) {
		
		HttpSession session = request.getSession();
		try {
			authenticalUser(request, session, login.getSsoId(), login.getPassword());
			
			Tokens tokens = authToken.getToken(login.getSsoId());
			response.setHeader("Whoever-Token", tokens.getToken());
			response.setHeader("Token-expiration", tokens.getTimeExp());
			
		} catch (Exception e) {
			e.printStackTrace();
			return "==> login fail";
		}
		return "=>> Login Success !!";
	}

	@RequestMapping(value = { "/mobile/anonymous/{langCode}" }, method = RequestMethod.GET,
			produces = "application/json")
	public @ResponseBody String loginAnonymous(HttpServletRequest request, HttpServletResponse response, @PathVariable("langCode") String langCode) {
		
		Languages language = langsService.findByCode(langCode);

		String ssoId = usersService.generateSsoId();
		String password = usersService.generatePassword();
		String idUser = usersService.generateUserId();
		HttpSession session = request.getSession();
		
		Users users = new Users(idUser, ssoId, password, States.active, true, true, language);
		try {
			usersService.registerUser(users);
			authenticalUser(request, session, ssoId, password);
			
			String token = authToken.initToken(users);
			String date = authToken.getTimeExpiration();
			response.setHeader("Whoever-Token", token);
			response.setHeader("Token-expiration", date);
				
		} catch (Exception e) {
			e.printStackTrace();
			return "Login with anonymous fails!!!";
		}
		return "Register successful!!!";
	}

	@RequestMapping(value = {"/mobile/register" }, method = RequestMethod.POST, 
			consumes = "application/json", produces = "application/json")
	public @ResponseBody String registerAccount(HttpServletRequest request, 
			HttpServletResponse response, @RequestBody CallRegister req) {
		
		Languages language = langsService.findByCode(req.getLangCode());
		String idUser = usersService.generateUserId();
		HttpSession session = request.getSession();
		
		Users users = null;
		if(req.getLocation() == null) {
			users = new Users(idUser, req.getSsoId(), req.getPassword(), 
					States.active, false, true, language);
		} else {
			users = new Users(idUser, req.getSsoId(), req.getPassword(), 
					States.active, req.getLocation().getxLoc(), req.getLocation().getyLoc(), false, true, language);
		}
		
		try {
			usersService.registerUser(users);
			authenticalUser(request, session, req.getSsoId(), req.getPassword());
			
			String token = authToken.initToken(users);
			String date = authToken.getTimeExpiration();
			response.setHeader("Whoever-Token", token);
			response.setHeader("Token-expiration", date);
		} catch (Exception e) {
			e.printStackTrace();
			return "Register fail!!!";
		}
		
		return "Register successful!!!";
	}

	@RequestMapping(value = { "/mobile/get/term" }, method = RequestMethod.GET,
			produces = "application/json")
	public @ResponseBody String acceptTermWhoever(@RequestParam(value = "lang", defaultValue = "en") String langCode) {
		
		return "";
	}

	@RequestMapping(value = { "/mobile/logout" }, method = RequestMethod.GET)
	public @ResponseBody String logoutWhoever(HttpSession session, 
			@RequestParam(value = "ssoId", required = true) String ssoId) {
		session.invalidate();
		return "Sucessful";
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

	private void authenticalUser(HttpServletRequest request, HttpSession session, String ssoId, String password) {
		session.invalidate();
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(ssoId, password);
		request.getSession();
		authToken.setDetails(new WebAuthenticationDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(authToken));
	}

}
