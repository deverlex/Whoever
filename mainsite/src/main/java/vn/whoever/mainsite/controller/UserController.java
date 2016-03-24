package vn.whoever.mainsite.controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.whoever.mainsite.model.request.ReqSignIn;

@Controller
public class UserController {
	
	@Autowired
	@Qualifier("appAuthenticationManager")
	private AuthenticationManager authManager;
	
	@RequestMapping(value = {"/", "/home", "access_denied", "/login"})
	public String homePage(ServletRequest request, ServletResponse response) {
		return "welcome";
	}
	
	@RequestMapping(value = {"/mobile/login"}, method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
	public @ResponseBody String mobileLogin(@RequestBody ReqSignIn reqSignIn, HttpServletRequest request) {
		return "welcome to Vietnam";
	}
}
