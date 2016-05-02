package vn.whoever.mainserver.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import v.whoever.service.impl.GenerateIdImpl;
import vn.whoever.mainserver.dao.ContactUserDao;
import vn.whoever.mainserver.dao.ContactsDao;
import vn.whoever.mainserver.dao.UsersDao;
import vn.whoever.mainserver.model.ContactUsers;
import vn.whoever.mainserver.model.Contacts;
import vn.whoever.mainserver.model.Users;
import vn.whoever.mainserver.service.AuthToken;
import vn.whoever.mainserver.service.ContactsService;

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
	
	public String generateContactId() {
		return GenerateIdImpl.generateId().getId();
	}

	public boolean createContact(String idUser) {
		Contacts contact = new Contacts(generateContactId(), idUser);
		try {
			contactsDao.createContact(contact);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public String findContact(String strFind) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addFriend(String idUser, String ssoIdFriend) {
		String idFriend = usersDao.findIdUser(ssoIdFriend);
		String idContact = contactsDao.getIdContact(idUser);
		contactUserDao.addContactUser(new ContactUsers(idContact, idFriend, true));
	}

	public List<String> getListFriends(HttpServletRequest request) {
		String idUser = authToken.getIdUserHttp(request);
		return contactUserDao.getListIdFriend(idUser, contactsDao.getIdContact(idUser));
	}

}
