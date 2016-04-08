package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;
import java.util.List;

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

	public Status getDetailStatus(String idStatus) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("idStatus", idStatus));
		return (Status) crit.uniqueResult();
	}

	public void deleteStatus(String idStatus) {
		deleteByKey(idStatus);
	}

	public List<Status> getListStatusByFriends(String idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Status> getListStatusContainNearby(String idUser) {
		// TODO Auto-generated method stub
		return null;
	}

}
