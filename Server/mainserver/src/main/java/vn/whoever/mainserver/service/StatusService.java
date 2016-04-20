package vn.whoever.mainserver.service;

import java.util.List;

import org.springframework.aop.IntroductionInterceptor;

import vn.whoever.mainserver.model.Status;
import vn.whoever.support.model.request.GetStatus;
import vn.whoever.support.model.request.InteractStatus;
import vn.whoever.support.model.utils.Interacts;
import vn.whoever.support.model.utils.Location;
import vn.whoever.support.model.utils.Order;

public interface StatusService {

	public String generateStatusId();
	public boolean postStatus(Status status);
	
	public List<Status> getListStatus(String idUser, Order order, int offset, double xLoc, double yLoc);
	
	public void interactStatus(String idStatus, InteractStatus interact);
	public Interacts getInteractStatusState(String idStatus, String idUser);
	public int getTotalLikes(String idStatus);
	public int getTotalDislikes(String idStatus);
	public int getTotalComments(String idStatus);
}
