package vn.whoever.mainsite.controller;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@RequestMapping(value = {"/", "/home", "access_denied", ".login"})
	public String homePage(ServletRequest request, ServletResponse response) {
		return "welcome";
	}
}
