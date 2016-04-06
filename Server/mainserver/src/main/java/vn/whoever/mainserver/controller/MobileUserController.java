package vn.whoever.mainserver.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import v.whoever.service.impl.GenerateSsoIdImpl;
import v.whoever.service.impl.GenerateTokenImpl;
import vn.whoever.mainserver.model.Languages;
import vn.whoever.mainserver.model.Profiles;
import vn.whoever.mainserver.model.SetRoles;
import vn.whoever.mainserver.model.Users;
import vn.whoever.mainserver.service.LanguagesService;
import vn.whoever.mainserver.service.ProfilesService;
import vn.whoever.mainserver.service.UsersService;
import vn.whoever.service.GenerateSsoId;
import vn.whoever.service.GenerateToken;
import vn.whoever.support.model.request.RequestAcceptTerm;
import vn.whoever.support.model.request.RequestLogin;
import vn.whoever.support.model.request.RequestRegister;
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
	
	//@Autowired
	//private ProfilesService profileService;
	
	@RequestMapping(value = {"/mobile/login"}, method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
	public @ResponseBody String loginWithAccount(HttpServletRequest request, HttpSession session,@RequestBody RequestLogin req) {
		if(req.getPassword().equals(""))
			return "login Fail";
		try {
			authenticalUser(request, session, req.getSsoId(), req.getPassword());
			String token = GenerateTokenImpl.getToken().getTokenId(req.getSsoId());
		} catch(Exception e) {
			e.printStackTrace();
			return "==> login fail";
		}
		return "=>> Login Success !!";
	}
	
	@RequestMapping(value = { "/mobile/anonymous/login" }, method = RequestMethod.GET)
	public @ResponseBody String loginAnonymous(HttpServletRequest request, HttpSession session,@RequestParam(value = "langCode", defaultValue = "vi") String langCode,
			@RequestParam(value = "birthday") @DateTimeFormat(pattern = "ddMMyyyy") Date birthday) {
		/**
		 * check birthday > 13 year old => oke
		 */
		Languages language = langsService.findByCode(langCode);

		String ssoId = usersService.generateSsoId();
		String password = usersService.generatePassword();
		String token = GenerateTokenImpl.getToken().getTokenId(ssoId);
		
		Users users = new Users(usersService.generateUserId(), ssoId,
				password, States.active, true, true, language);
		try {
			usersService.registerUser(users);
			authenticalUser(request, session, ssoId, password);
		} catch (Exception e) {
			e.printStackTrace();
			return "Login with anonymous fails!!!";
		}
		return "Register successful!!!";
	}

	@RequestMapping(value = {"/mobile/register"}, method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
	public @ResponseBody String registerAccount(HttpServletRequest request, HttpSession session,@RequestBody RequestRegister req) {
		Languages language = langsService.findByCode(req.getLangCode());
		Users users = new Users(usersService.generateUserId(), req.getSsoId(), req.getPassword(), States.active, false, true, language);
		try {
			usersService.registerUser(users);
			authenticalUser(request, session, req.getSsoId(), req.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			return "Register fail!!!";
		}
		return "Register successful!!!";
	}
	
	@RequestMapping(value = {"/mobile/accept_term"}, method = RequestMethod.POST)
	public @ResponseBody String acceptTermWhoever(HttpSession session, @RequestBody RequestAcceptTerm acceptTerm) {

		return "";
	}
	
	@RequestMapping(value = {"/mobile/logout"}, method = RequestMethod.GET)
	public @ResponseBody String logoutWhoever(HttpSession session) {
		
		return "Sucessful";
	}

	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	private void authenticalUser(HttpServletRequest request, HttpSession session,String ssoId, String password) {
		session.invalidate();
		UsernamePasswordAuthenticationToken authToken = 
				new UsernamePasswordAuthenticationToken(ssoId, password);
		request.getSession();
		authToken.setDetails(new WebAuthenticationDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(authToken));
	}

}
