package vn.whoever.mainserver.service;

import java.util.List;

import vn.whoever.mainserver.model.Status;

public interface StatusService {

	public Boolean postStatus(Status status);
	public List<Status> getListStatus(String ssoId);
	public Status getDetailStatus(String idStatus);
}
