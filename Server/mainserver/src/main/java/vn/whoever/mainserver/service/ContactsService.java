package vn.whoever.mainserver.service;

public interface ContactsService {

	public String generateContactId();
	public boolean createContact(String idUser);
	public String findContact(String strFind);
}
