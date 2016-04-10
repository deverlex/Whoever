package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
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
	public List<Status> getListStatusByFriends(List<String> listFriends, int offset) {
		Criteria crit = createEntityCriteria();		
		crit.add(Restrictions.in("idUser", listFriends));
		crit.addOrder(Order.desc("timeUp"));
		crit.setFirstResult(offset);
		crit.setMaxResults(12);
		return (List<Status>) crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Status> getListStatusContainNearby(List<String> listFriends, double xLoc, double yLoc, int offset) {
		final double ratio = 0.0003;
		
		Criteria crit = createEntityCriteria();
		
		Criterion critFr = Restrictions.in("idUser", listFriends);
		Criterion critNearX = Restrictions.and(
				Restrictions.between("xLoc", xLoc - offset*ratio, xLoc +  offset*ratio),
				Restrictions.between("yLoc", yLoc - offset*ratio, yLoc + offset*ratio));
		
		crit.add(Restrictions.or(critNearX, critFr));
		crit.addOrder(Order.desc("timeUp"));
		crit.setFirstResult(offset);
		crit.setMaxResults(12);

		return (List<Status>) crit.list();
	}
	
//	private List<Status> getList(Map<String, Status> map) {
//		Collection<Status> values = map.values();
//		List<Status> status = new ArrayList<Status>(values);
//		return status;
//	}



}
