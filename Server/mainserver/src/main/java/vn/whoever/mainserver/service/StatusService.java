package vn.whoever.mainserver.service;

import java.util.List;

import vn.whoever.mainserver.model.Status;
import vn.whoever.support.model.request.InteractStatus;
import vn.whoever.support.model.utils.Interacts;
import vn.whoever.support.model.utils.Order;

public interface StatusService {

	public String generateStatusId();
	public void postStatus(Status status);
	
	public void updateStatus(Status status);
	public Status getStatus(String idStatus);
	
	
	public List<Status> getListStatus(String idUser, Order order, int offset, double xLoc, double yLoc);
	
	public void interactStatus(String idStatus, InteractStatus interactStt);
	public Interacts getInteractStatusState(String idStatus, String idUser);
	public int getTotalLikes(String idStatus);
	public int getTotalDislikes(String idStatus);
	public int getTotalComments(String idStatus);
}
