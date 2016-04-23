package vn.whoever.mainserver.dao;

import java.util.List;

import vn.whoever.mainserver.model.Status;

public interface StatusDao {
	
	public void postStatus(Status status);
	public void updateStatus(Status status);
	public Status getStatus(String idStatus);
	
	public List<Status> getListStatusHome(String idLanguage);
	public List<Status> getListStatusByFriends(List<String> idFriends, String idUser, int offset);
	public List<Status> getListStatusContainNearby(List<String> idFriends, String iduser, Double xLoc, Double yLoc, int offset);
	public void deleteStatus(String idStatus);
	
}
