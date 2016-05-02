package vn.whoever.mainserver.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface ContactsService {

	public String generateContactId();
	public boolean createContact(String idUser);
	public void addFriend(String idUser, String ssoIdFriend);
	
	public String findContact(String strFind);
	
	public List<String> getListFriends(HttpServletRequest request);
	
}
