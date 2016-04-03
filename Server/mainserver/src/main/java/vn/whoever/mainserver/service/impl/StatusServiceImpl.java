package vn.whoever.mainserver.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.whoever.mainserver.model.Status;
import vn.whoever.mainserver.service.StatusService;

@Service("statusService")
@Transactional
public class StatusServiceImpl implements StatusService {

	
	
	public List<Status> getListStatus(String ssoId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Status getDetailStatus(String idStatus) {
		// TODO Auto-generated method stub
		return null;
	}

}
