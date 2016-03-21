package vn.whoever.server.controller.impl.mobile;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import vn.whoever.server.controller.MainController;

@RestController
public class MainControllerImpl implements MainController {

	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public String login(@RequestParam(value = "username", required = true)String username,
			@RequestParam(value = "password")String password, @RequestHeader("Accept") String acceptHeader,
			HttpSession session) {
		
		System.out.println("Gui thong tin len server: " + username);
		session.setAttribute("user", username);
		
		return "Ngu xi dan don";
	}
	
	

}
