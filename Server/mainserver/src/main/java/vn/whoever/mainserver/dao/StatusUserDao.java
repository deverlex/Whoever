package vn.whoever.mainserver.dao;

import vn.whoever.support.model.utils.Interacts;

public interface StatusUserDao {
	
	public void addInteractStatus(String idStatus, String idUser, Interacts interact);

	public Interacts getInteractStateStatus(String idStatus, String idUser);
	
	public int getTotalInteract(String idStatus, Interacts interact);
}
