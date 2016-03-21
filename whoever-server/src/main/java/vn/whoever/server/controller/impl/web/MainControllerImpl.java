package vn.whoever.server.controller.impl.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.whoever.server.controller.MainController;

@Controller
public class MainControllerImpl implements MainController {

	@RequestMapping(value = "/login")
	public String login(String username, String password, String acceptHeader, HttpSession session) {
		// TODO Auto-generated method stub
		return "index";
	}
	
	@RequestMapping(value = "/checkout")
	public String checkOut() {
		return "index";
	}

}
