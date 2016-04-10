package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hibernate.Criteria;
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

	@SuppressWarnings("unchecked")
	public List<Status> getListStatusByFriends(List<String> listFriends, int levelGet) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.in("idUser", listFriends));
		return (List<Status>) crit.list();
	}
	
	public List<Status> getListStatusContainNearby(List<String> listFriends, double xLoc, double yLoc, int levelGet) {
		// TODO contain: friend and nearby
		// do get by friends
		Map<String, Status> mapStatus = new ConcurrentHashMap<String, Status>();
		for (Status status : getListStatusByFriends(listFriends, levelGet)) {
			mapStatus.put(status.getIdStatus(), status);
		}
		// get status nearby
		Criteria crit = createEntityCriteria();

		// xLoc & yLoc grow by level
		crit.add(Restrictions.between("xLoc", 10, 100)).add(Restrictions.between("yLoc", 10, 100));

		return getList(mapStatus);
	}
	
	private List<Status> getList(Map<String, Status> map) {
		Collection<Status> values = map.values();
		List<Status> status = new ArrayList<Status>(values);
		return status;
	}



}
