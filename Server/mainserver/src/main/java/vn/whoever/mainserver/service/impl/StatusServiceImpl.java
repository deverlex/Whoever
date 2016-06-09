package vn.whoever.mainserver.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.whoever.mainserver.dao.CommentsDao;
import vn.whoever.mainserver.dao.ContactUserDao;
import vn.whoever.mainserver.dao.ContactsDao;
import vn.whoever.mainserver.dao.LanguagesDao;
import vn.whoever.mainserver.dao.StatusDao;
import vn.whoever.mainserver.dao.StatusUserDao;
import vn.whoever.mainserver.model.Status;
import vn.whoever.mainserver.service.StatusService;
import vn.whoever.service.impl.GenerateIdImpl;
import vn.whoever.support.model.request.UserInteract;
import vn.whoever.support.model.utils.Interacts;
import vn.whoever.support.model.utils.Order;

/**
 * @author Nguyen Van Do
 * 
 *	Service for handle functions concerned to status
 */

@Service("statusService")
@Transactional
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusDao statusDao;

	@Autowired
	private ContactsDao contactsDao;

	@Autowired
	private ContactUserDao contactUserDao;

	@Autowired
	private StatusUserDao statusUserDao;

	@Autowired
	private CommentsDao commentsDao;

	@Autowired
	private LanguagesDao languagesDao;

	public String generateStatusId() {
		return GenerateIdImpl.generateId().getId();
	}

	public void postStatus(Status status) {
		statusDao.postStatus(status);
	}

	public void updateStatus(Status status) {
		statusDao.updateStatus(status);
	}

	public Status getStatus(String idStatus) {
		return statusDao.getStatus(idStatus);
	}

	// This method isn't complete
	public List<Status> getHomeStatus(String langCode) {
		int idLanguage = languagesDao.findIdByCode(langCode);
		return null;
	}

	/**
	 * Get status with two mode: friends || nearby
	 */
	public List<Status> getListStatus(String idUser, Order order, int offset, double xLoc, double yLoc) {
		List<String> lIdFriend = contactUserDao.getListIdFriend(idUser, contactsDao.getIdContact(idUser));
		List<Status> listStatus = new LinkedList<Status>();
		
		// get status of friends
		if (order == Order.friends) {
			listStatus = statusDao.getListStatusByFriends(lIdFriend, idUser, offset);
		} else {
			// get status of users nearby
			listStatus = statusDao.getListStatusContainNearby(lIdFriend, idUser, xLoc, yLoc, offset);
		}
		return listStatus;
	}
	
	public void statusInteract(String idStatus, String idUser, UserInteract interactStt) {
		statusUserDao.addInteractStatus(idStatus, idUser, interactStt.getInteract());
		Status status = statusDao.getStatus(idStatus);
		// when like/dislike/comment status -> update time status for sorting status service
		status.setTimeUp(new Date());
		statusDao.updateStatus(status);
	}

	public Interacts getInteractStatusState(String idStatus, String idUser) {
		return statusUserDao.getInteractStateStatus(idStatus, idUser);
	}

	public int getTotalLikes(String idStatus) {
		return statusUserDao.getTotalInteract(idStatus, Interacts.like);
	}

	public int getTotalDislikes(String idStatus) {
		return statusUserDao.getTotalInteract(idStatus, Interacts.dislike);
	}

	public int getTotalComments(String idStatus) {
		return commentsDao.getTotalCommentStatus(idStatus);
	}
}
