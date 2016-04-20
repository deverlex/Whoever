package vn.whoever.mainserver.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.whoever.mainserver.service.AuthToken;
import vn.whoever.mainserver.service.ContactsService;
import vn.whoever.support.model.request.AddContact;

@Controller
public class MobileContactController {

	@Autowired
	private ContactsService contactService;
	
	private AuthToken authToken;
	
	@RequestMapping(value = {"/mobile/friends"}, method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
	public @ResponseBody String addFriend(HttpServletRequest request, HttpServletResponse response, 
			@RequestBody AddContact addContact) {
		
		if(contactService.addFriend(authToken.getIdUserHttp(request), addContact.getSsoIdFriend())) {
			return "add friend successfull";
		}
		return "add friend false";
	}
	
	@RequestMapping(value = {"/mobile/friends/{ssoId}"}, method = RequestMethod.DELETE)
	public String deleteFriend(@PathVariable(value = "ssoId") String ssoId) {
		
		return "";
	}
}
