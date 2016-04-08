package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.StatusDao;
import vn.whoever.mainserver.model.Status;

@Repository("statusDao")
public class StatusDaoImpl extends AbstractDao<String, Status> implements StatusDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 164545334L;

	public void postStatus(Status status) {
		persist(status);
	}

	public void updateStatus(Status status) {
		persist(status);
	}

	public void deleteStatus(String idStatus) {
		deleteByKey(idStatus);
	}

	public List<Status> getListStatusByFriends(String idUser) {
		
		return null;
	}

	public List<Status> getListStatusContainNearby(String idUser) {
		// TODO contain: friend and nearby
		//do get by friends
		Map<String, Status> mapStatus = new ConcurrentHashMap<String, Status>();
		for (Status status : getListStatusByFriends(idUser)) {
			mapStatus.put(status.getIdStatus(), status);
		}
		// get status nearby
		Criteria crit = createEntityCriteria();
		
		// xLoc & yLoc grow by level
		crit.add(Restrictions.between("xLoc", 10, 100))
		.add(Restrictions.between("yLoc", 10, 100));
		
		
		
		return getList(mapStatus);
	}
	
	private List<Status> getList(Map<String, Status> map) {
		Collection<Status> values = map.values();
		List<Status> status = new ArrayList<Status>(values);
		return status;
	}

}
