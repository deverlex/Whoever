package vn.whoever.mainserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import v.whoever.service.impl.GenerateIdImpl;
import vn.whoever.mainserver.dao.ContactsDao;
import vn.whoever.mainserver.model.Contacts;
import vn.whoever.mainserver.service.ContactsService;

@Service("contactService")
@Transactional
public class ContactsServiceImpl implements ContactsService {

	@Autowired
	private ContactsDao contactsDao;
	
	public String generateContactId() {
		return GenerateIdImpl.generateId().getId();
	}

	public boolean createContact(String idUser) {
		Contacts contact = new Contacts(generateContactId(), idUser);
		System.out.println("Create contacts");
		return contactsDao.createContact(contact);
	}

	public String findContact(String strFind) {
		// TODO Auto-generated method stub
		return null;
	}

}
