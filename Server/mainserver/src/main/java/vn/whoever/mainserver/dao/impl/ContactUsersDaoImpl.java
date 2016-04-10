package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;

import org.springframework.web.bind.annotation.ResponseBody;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.ContactUserDao;
import vn.whoever.mainserver.model.ContactUsers;

public class ContactUsersDaoImpl extends AbstractDao<String, ContactUsers> implements ContactUserDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -43435465771L;

	public void addContactUser(ContactUsers contactUsers) {
		persist(contactUsers);
	}

	
}
