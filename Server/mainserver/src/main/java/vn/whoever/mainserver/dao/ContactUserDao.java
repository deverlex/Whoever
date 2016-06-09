package vn.whoever.mainserver.dao;

import java.util.List;

import vn.whoever.mainserver.model.ContactUsers;

public interface ContactUserDao {

	public void addContactUser(ContactUsers contactUsers);

	public List<String> getListIdFriend(String idUser, String idContact);
}
