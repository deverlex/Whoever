package vn.whoever.mainserver.dao;

import vn.whoever.mainserver.model.Contacts;

public interface ContactsDao {

	public String getIdContact(String idUser);

	public void createContact(Contacts contact);
}
