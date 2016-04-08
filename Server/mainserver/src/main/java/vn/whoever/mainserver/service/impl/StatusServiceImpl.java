package vn.whoever.mainserver.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import v.whoever.service.impl.GenerateIdImpl;
import v.whoever.service.impl.GenerateSsoIdImpl;
import vn.whoever.mainserver.dao.StatusDao;
import vn.whoever.mainserver.model.Status;
import vn.whoever.mainserver.service.StatusService;

@Service("statusService")
@Transactional
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusDao statusDao;
	
	public String generateStatusId() {
		return GenerateIdImpl.generateId().getId();
	}
	
	public boolean postStatus(Status status) {
		try {
			statusDao.postStatus(status);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public List<Status> getListStatus(String ssoId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Status getDetailStatus(String idStatus) {
		// TODO Auto-generated method stub
		return null;
	}

}
