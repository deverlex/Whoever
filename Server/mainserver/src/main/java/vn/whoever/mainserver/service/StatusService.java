package vn.whoever.mainserver.service;

import java.util.List;

import vn.whoever.mainserver.model.Status;
import vn.whoever.support.model.utils.Order;

public interface StatusService {

	public String generateStatusId();
	public boolean postStatus(Status status);
	public List<Status> getListStatus(String str);
}
