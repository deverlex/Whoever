package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.ContactUserDao;
import vn.whoever.mainserver.model.ContactUsers;
import vn.whoever.mainserver.model.Users;

@Repository
public class ContactUsersDaoImpl extends AbstractDao<String, ContactUsers> implements ContactUserDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -43435465771L;

	public void addContactUser(ContactUsers contactUsers) {
		persist(contactUsers);
	}

	
	@SuppressWarnings("unchecked")
	public List<String> getListIdFriend(String idContact) {
//		String sql = "select idUser from Contacts_Users where idContact = '" + idContact + "'";
//		Query query = getSession().createQuery(sql);
//		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("idContact", idContact));
		List<String> listIdFriend = new ArrayList<String>();
		for (ContactUsers friend : (List<ContactUsers>) crit.list()) {
			listIdFriend.add(friend.getIdUser());
		}
		return listIdFriend;
	}

	
}
