package vn.whoever.mainserver.dao;

import java.util.List;

import vn.whoever.mainserver.model.Status;

public interface StatusDao {
	
	public void postStatus(Status status);
	public void updateStatus(Status status);
	public List<Status> getListStatusByFriends(List<String> idFriends, int levelGet);
	public List<Status> getListStatusContainNearby(List<String> idFriends, double xLoc, double yLoc, int levelGet);
	public void deleteStatus(String idStatus);
	
}
