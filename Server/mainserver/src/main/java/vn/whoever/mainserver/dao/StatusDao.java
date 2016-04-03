package vn.whoever.mainserver.dao;

import java.util.List;

import vn.whoever.mainserver.model.Status;

public interface StatusDao {
	
	public void upLoadStatus(Status status);
	public void updateStatus(Status status);
	public List<Status> getListStatusByFriends(String idUser);
	public List<Status> getListStatusContainNearby(String idUser);
	public Status getDetailStatus(String idStatus);
	public void deleteStatus(String idStatus);
	
}
