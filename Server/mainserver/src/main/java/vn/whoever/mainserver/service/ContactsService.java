package vn.whoever.mainserver.service;

public interface ContactsService {

	public String generateContactId();
	public boolean createContact(String idUser);
	public boolean addFriend(String ssoId, String ssoIdFriend);
	
	public String findContact(String strFind);
	
}
