package vn.whoever.mainsite.controller.mobile.impl;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MobileUserController {

	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public @ResponseBody String homePage(ServletRequest request, ServletResponse response) {
		return "Chao Mung den voi server mobile";
	}
	
}
