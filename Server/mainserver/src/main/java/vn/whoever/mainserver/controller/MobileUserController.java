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

import vn.whoever.mainserver.dao.LanguagesDao;
import vn.whoever.mainserver.model.Users;
import vn.whoever.mainserver.service.LanguagesService;
import vn.whoever.mainserver.service.UsersService;
import vn.whoever.support.model.request.RequestAcceptTerm;
import vn.whoever.support.model.request.RequestLogin;
import vn.whoever.support.model.request.RequestRegister;
import vn.whoever.support.utils.GenerateSsoID;

@Controller
public class MobileUserController {

	@Autowired
	@Qualifier("whoeverAuthenticationManager")
	protected AuthenticationManager authManager;
	
	@Autowired
	private LanguagesService langsService;
	
	@Autowired
	private UsersService usersService;
	
	
	@RequestMapping(value = {"/mobile/login"}, method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
	public @ResponseBody String loginWithAccount(HttpServletRequest request, HttpSession session,@RequestBody RequestLogin requestLogin) {
		if(requestLogin.getPassword().equals(""))
			return "login Fail";
		
		try {
			UsernamePasswordAuthenticationToken authToken = 
					new UsernamePasswordAuthenticationToken(requestLogin.getSsoId(), requestLogin.getPassword());
			request.getSession();
			authToken.setDetails(new WebAuthenticationDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(authToken));
		} catch(Exception e) {
			e.printStackTrace();
			return "==> login fail";
		}
		
		return "=>> Login Success !!";
	}
	
	@RequestMapping(value = {"/mobile/anonymous/login"}, method = RequestMethod.GET)
	public String loginAnonymous(@RequestParam(value = "langCode", defaultValue = "vi") String langCode,
			@RequestParam(value = "birthday") @DateTimeFormat(pattern = "dd/MM/yyyy") Date birthday) {
		
		
		
		
		return "";
	}

	@RequestMapping(value = {"/mobile/register"}, method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
	public @ResponseBody void registerAccount(@RequestBody RequestRegister req) {
		
//		Users users = new Users(req.getSsoId(), req.getPassword(),
//				langsService.findByCode(req.getLangCode()).getIdLanguage(),
//				req.getNickName(), req.getBirthday());
//		usersService.registerUser(users);
		
//		System.out.println("langCode: " + req.getLangCode());
//		System.out.println("findByLangcode: " + langsService.findByCode(req.getLangCode()).getStandardName());
	}
	
	@RequestMapping(value = {"/mobile/accept_term"}, method = RequestMethod.POST)
	public @ResponseBody String acceptTermWhoever(HttpSession session, @RequestBody RequestAcceptTerm acceptTerm) {

		return "";
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


}
