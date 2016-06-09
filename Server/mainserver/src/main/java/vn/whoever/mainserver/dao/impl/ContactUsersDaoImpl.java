package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.ContactUserDao;
import vn.whoever.mainserver.dao.ContactsDao;
import vn.whoever.mainserver.model.ContactUsers;
/**
 * @author Nguyen Van Do
 *	
 *	This class provide accessing to database that concern linked between user's contact and user.
 */
@Repository
public class ContactUsersDaoImpl extends AbstractDao<String, ContactUsers> implements ContactUserDao, Serializable {

	@Autowired
	private ContactsDao contactsDao;

	private static final long serialVersionUID = -43435465771L;

	public void addContactUser(ContactUsers contactUsers) {
		persist(contactUsers);
	}

	/*
	 * This method provide list friends using idUser and idContact
	 */
	@SuppressWarnings("unchecked")
	public List<String> getListIdFriend(String idUser, String idContact) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("idContact", idContact));
		List<String> listIdFriend = new LinkedList<String>();
		for (ContactUsers friend : (List<ContactUsers>) crit.list()) {
			if (isFriend(idUser, friend.getIdUser())) {
				listIdFriend.add(friend.getIdUser());
			}
		}
		return listIdFriend;
	}

	private Boolean isFriend(String idUser, String idFollow) {
		String idContact = contactsDao.getIdContact(idFollow);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.and(Restrictions.eq("idContact", idContact), Restrictions.eq("idUser", idUser)));
		return crit.uniqueResult() != null ? true : false;
	}
}
