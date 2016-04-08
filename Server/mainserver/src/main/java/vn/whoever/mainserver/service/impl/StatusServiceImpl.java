package vn.whoever.mainserver.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import v.whoever.service.impl.GenerateIdImpl;
import vn.whoever.mainserver.dao.StatusDao;
import vn.whoever.mainserver.dao.UsersDao;
import vn.whoever.mainserver.model.Status;
import vn.whoever.mainserver.model.Users;
import vn.whoever.mainserver.service.StatusService;
import vn.whoever.support.model.request.GetStatus;
import vn.whoever.support.model.utils.Order;

@Service("statusService")
@Transactional
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusDao statusDao;
	
	@Autowired
	private UsersDao usersDao;
	
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
	
	public List<Status> getListStatus(GetStatus getStatus) {
		String ssoId = getStatus.getSsoId();
		Users users = usersDao.findBySsoId(ssoId);
		if(getStatus.getOrder() == Order.friends) {
			statusDao.getListStatusByFriends(users.getIdUser());
		} else {
			statusDao.getListStatusContainNearby(users.getIdUser());
		}
		return null;
	}

	public Status getDetailStatus(String idStatus) {
		// TODO Auto-generated method stub
		return null;
	}

}
