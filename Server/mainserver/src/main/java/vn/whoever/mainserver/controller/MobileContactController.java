package vn.whoever.mainserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.whoever.mainserver.service.ContactsService;
import vn.whoever.support.model.request.AddContact;

@Controller
public class MobileContactController {

	@Autowired
	private ContactsService contactService;
	
	@RequestMapping(name = "/mobile/add/friend", method = RequestMethod.PUT,
			consumes = "application/json", produces = "application/json")
	public @ResponseBody String addFriend(HttpServletResponse response, @RequestBody AddContact addContact) {
		
		return "add friend successfull";
	}
}
