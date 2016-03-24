package vn.whoever.mainsite.controller.web.impl;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.whoever.mainsite.controller.UserController;

@Controller
public class WebUserController implements UserController {

	@RequestMapping(value = {"/", "/home"})
	public String homePage(ServletRequest request, ServletResponse response) {
		return "welcome";
	}
}
