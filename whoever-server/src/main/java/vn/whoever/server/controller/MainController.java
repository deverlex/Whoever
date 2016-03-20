package vn.whoever.server.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import vn.whoever.server.entity.User;

public interface MainController {

	public Object login(String username, String password, String acceptHeader, HttpSession session);
	
}
