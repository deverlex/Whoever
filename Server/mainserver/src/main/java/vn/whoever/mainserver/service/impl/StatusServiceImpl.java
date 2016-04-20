package vn.whoever.mainserver.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import v.whoever.service.impl.GenerateIdImpl;
import vn.whoever.mainserver.dao.ContactUserDao;
import vn.whoever.mainserver.dao.ContactsDao;
import vn.whoever.mainserver.dao.StatusDao;
import vn.whoever.mainserver.dao.StatusUserDao;
import vn.whoever.mainserver.dao.UsersDao;
import vn.whoever.mainserver.model.Profiles;
import vn.whoever.mainserver.model.Status;
import vn.whoever.mainserver.model.Users;
import vn.whoever.mainserver.service.ProfilesService;
import vn.whoever.mainserver.service.StatusService;
import vn.whoever.support.model.request.GetStatus;
import vn.whoever.support.model.request.InteractStatus;
import vn.whoever.support.model.utils.Interacts;
import vn.whoever.support.model.utils.Location;
import vn.whoever.support.model.utils.Order;

@Service("statusService")
@Transactional
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusDao statusDao;
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private ContactsDao contactsDao;
	
	@Autowired
	private ContactUserDao contactUserDao;
	
	@Autowired
	private StatusUserDao statusUserDao;
	
	public String generateStatusId() {
		return GenerateIdImpl.generateId().getId();
	}
	
	public boolean postStatus(Status status) {
		try {
			statusDao.postStatus(status);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * This is heart of Whoever project.
	 * @param getStatus
	 * @return
	 */
	
	public List<Status> getListStatus(String idUser, Order order, int offset, Location location) {
		List<String> lIdFriend = contactUserDao.getListIdFriend(idUser, contactsDao.getIdContact(idUser));
		List<Status> listStatus = new LinkedList<Status>();
		
		if(order == Order.friends) {
			if(lIdFriend.size() > 0)
				listStatus = statusDao.getListStatusByFriends(lIdFriend, idUser, offset);
		} else {
			listStatus = statusDao.getListStatusContainNearby(lIdFriend, idUser,
					location.getxLoc(), location.getyLoc(), offset);
		}
		return listStatus;
	}

	public void interactStatus(String idStatus, InteractStatus interact) {
		String idUser = usersDao.findIdUser(interact.getSsoId());
		statusUserDao.addInteractStatus(idStatus, idUser, interact.getInteract());
	}

	public Interacts getInteractStatusState(String idStatus, String idUser) {
		return statusUserDao.getInteractStateStatus(idStatus, idUser);
	}

	public Integer getTotalLikes(String idStatus) {
		return statusUserDao.getTotalInteract(idStatus, Interacts.like);
	}

	public Integer getTotalDislikes(String idStatus) {
		return statusUserDao.getTotalInteract(idStatus, Interacts.dislike);
	}

	public Integer getTotalComments(String idStatus) {
		// TODO Auto-generated method stub
		return 0;
	}
}
