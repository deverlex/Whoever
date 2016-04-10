package vn.whoever.mainserver.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.ContactsDao;
import vn.whoever.mainserver.model.Contacts;

@Repository
public class ContactsDaoImpl extends AbstractDao<String, Contacts> implements ContactsDao {

	public String getIdContact(String idUser) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("idUser", idUser));
		return ((Contacts) crit.uniqueResult()).getIdContact();
	}

	public void createContact(Contacts contact) {
		persist(contact);
	}

}
