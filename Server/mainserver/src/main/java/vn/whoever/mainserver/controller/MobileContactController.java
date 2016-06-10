package vn.whoever.mainserver.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.whoever.mainserver.model.Profiles;
import vn.whoever.mainserver.model.Users;
import vn.whoever.mainserver.service.AuthToken;
import vn.whoever.mainserver.service.ContactsService;
import vn.whoever.mainserver.service.ProfilesService;
import vn.whoever.mainserver.service.UsersService;
import vn.whoever.support.response.ReturnContact;
import vn.whoever.support.response.ReturnSearchContact;
import vn.whoever.support.utils.TimeUp;

/**
 * @author Nguyen Van Do
 * 
 * This file provide comment status function: get list contacts, 
 * add new contact/follow a friend/person
 */

@Controller
@RequestMapping("/mobile/friends")
public class MobileContactController {

	@Autowired
	private ContactsService contactService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private ProfilesService profilesService;

	@Autowired
	private AuthToken authToken;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<ReturnContact> getFriends(HttpServletRequest request) {

		List<ReturnContact> returnContacts = new LinkedList<ReturnContact>();
		List<String> idFriends = contactService.getListIdFriends(request);

		// set information for response action get a contact
		for (String idFriend : idFriends) {
			ReturnContact contact = new ReturnContact();
			Users users = usersService.findByIdUser(idFriend);
			contact.setSsoId(users.getSsoId());
			contact.setLatestOnline((new TimeUp(users.getTimeUp()).getTime()));
			contact.setNickName(profilesService.getNickName(idFriend));
			returnContacts.add(contact);
		}
		return returnContacts;
	}

	/**
	 * This method for add a new friend action of users.
	 * 
	 */
	@RequestMapping(value = {"/{ssoId}" }, method = RequestMethod.POST, 
			consumes = "application/json", produces = "application/json")
	public @ResponseBody void addFriend(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(value = "ssoId") String ssoId) {

		try {
			contactService.addFriend(authToken.getIdUserHttp(request), ssoId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method for searching a contact exist in Whoever system Search by
	 * username and ssoId
	 * 
	 * Information responsive is described this below { "ssoId" : "", "nickName"
	 * : "", "latestOnline" : " }
	 */
	@RequestMapping(value = {"/search/{query}" }, method = RequestMethod.GET, 
			consumes = "application/json", produces = "application/json")
	public @ResponseBody List<ReturnSearchContact> queryContact(HttpServletRequest request,
			HttpServletResponse response, @PathVariable(value = "query") String query) {
		Map<String, ReturnSearchContact> mapReturn = new HashMap<String, ReturnSearchContact>();

		// return a map contain user is queried by ssoId
		for (Users user : usersService.queryIdUserBySsoId(query)) {
			// When user is anonymous or choice anonymous mode on this social
			// information response hiding some info of contact.
			if (!user.getIsAnonymous()) {
				ReturnSearchContact contact = new ReturnSearchContact(user.getSsoId(), null, null, false);
				contact.setAvatart(null);
				contact.setNickName(profilesService.getNickName(user.getIdUser()));
				mapReturn.put(user.getIdUser(), contact);
			}
		}

		// return a map contain user is queried by usernname/nickname
		for (Profiles profile : profilesService.queryIdUserByNickName(query)) {
			if (mapReturn.get(profile.getIdUser()) == null) {
				ReturnSearchContact contact = new ReturnSearchContact(null, profile.getNickName(), null, false);
				contact.setSsoId(usersService.findSsoIdbyIdUser(profile.getIdUser()));
				mapReturn.put(profile.getIdUser(), contact);
			}
		}

		// set information list friend to map responsive
		for (String idFriend : contactService.getListIdFriends(request)) {
			if (mapReturn.get(idFriend) != null) {
				mapReturn.get(idFriend).setIsFriend(true);
			}
		}

		// set information to message responsive
		List<ReturnSearchContact> returnList = new ArrayList<ReturnSearchContact>();
		for (Entry<String, ReturnSearchContact> item : mapReturn.entrySet()) {
			returnList.add(item.getValue());
		}

		System.out.println("size return: " + returnList.size());
		return returnList;
	}
}
