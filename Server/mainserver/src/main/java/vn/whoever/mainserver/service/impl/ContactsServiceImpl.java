package vn.whoever.mainserver.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.whoever.mainserver.dao.ContactUserDao;
import vn.whoever.mainserver.dao.ContactsDao;
import vn.whoever.mainserver.dao.UsersDao;
import vn.whoever.mainserver.model.ContactUsers;
import vn.whoever.mainserver.model.Contacts;
import vn.whoever.mainserver.service.AuthToken;
import vn.whoever.mainserver.service.ContactsService;
import vn.whoever.service.impl.GenerateIdImpl;
/**
 * @author Nguyen Van Do
 *
 *	This class provide functions concerned contact service.
 *	Example: get list contact, find contact, create contact.
 *	 
 */
@Service("contactService")
@Transactional
public class ContactsServiceImpl implements ContactsService {

	@Autowired
	private ContactsDao contactsDao;
	
	@Autowired
	private ContactUserDao contactUserDao;
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private AuthToken authToken;
	
	// Generation contact Id service
	public String generateContactId() {
		return GenerateIdImpl.generateId().getId();
	}

	public void createContact(String idUser) {
		Contacts contact = new Contacts(generateContactId(), idUser);	
		contactsDao.createContact(contact);
	}

	// This method isn't complete
	public String findContact(String strFind) {
		return null;
	}

	public void addFriend(String idUser, String ssoIdFriend) {
		String idFriend = usersDao.findIdUser(ssoIdFriend);
		String idContact = contactsDao.getIdContact(idUser);

		contactUserDao.addContactUser(new ContactUsers(idContact, idFriend, true));
	}
	
	public List<String> getListIdFriends(HttpServletRequest request) {
		String idUser = authToken.getIdUserHttp(request);
		return contactUserDao.getListIdFriend(idUser, contactsDao.getIdContact(idUser));
	}

}
