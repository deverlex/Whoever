package vn.whoever.mainserver.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.whoever.support.model.request.RequestAcceptTerm;
import vn.whoever.support.model.request.RequestLogin;
import vn.whoever.support.model.request.RequestRegister;
import vn.whoever.support.model.request.RequestStatus;
import vn.whoever.support.utils.GenerateSsoID;

@Controller
public class UserController {

	@Autowired
	@Qualifier("whoeverAuthenticationManager")
	protected AuthenticationManager authManager;
	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		// TODO: model set somethings argument
		return "homePage";
	}
	
	
	
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
		String ssoId = GenerateSsoID.getInstance().getSsoId();
		
		
		
		return "";
	}

	@RequestMapping(value = {"/mobile/register"}, method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
	public @ResponseBody String registerAccount(@RequestBody RequestStatus requestRegister) {
		System.out.println(requestRegister.getSsoId());
		System.out.println(requestRegister.getStatus().get(0).getContent());
		return "";
	}
	
	@RequestMapping(value = {"/mobile/accept_term"}, method = RequestMethod.POST)
	public @ResponseBody String acceptTermWhoever(HttpSession session, @RequestBody RequestAcceptTerm acceptTerm) {

		return "";
	}
	
	
	
	
	/**
	 * Thuoc phan code sau
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
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
