package vn.whoever.mainserver.dao;

import vn.whoever.support.model.utils.Interacts;

public interface StatusUserDao {
	
	public void interactStatus(String idStatus, String idUser, Interacts interact);
}
