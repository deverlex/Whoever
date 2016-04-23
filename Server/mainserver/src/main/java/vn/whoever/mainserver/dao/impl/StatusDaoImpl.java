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
import vn.whoever.support.model.utils.Privacies;

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
		update(status);
	}

	public void deleteStatus(String idStatus) {
		deleteByKey(idStatus);
	}
	
	/**
	 * All need add privacy = 'normal' || 'open' with friend, nearby only get privacy = 'open'
	 * 
	 */

	@SuppressWarnings("unchecked")
	public List<Status> getListStatusByFriends(List<String> listFriends, String idUser, int offset) {
		Criteria crit = createEntityCriteria();
		
		Criterion critSelf = Restrictions.eq("idUser", idUser);
		System.out.println("idUser: " + idUser);
		
		if(listFriends.size() > 0){
			Criterion critPrivacy = Restrictions.or(
					Restrictions.eq("privacy", Privacies.normal), 
					Restrictions.eq("privacy", Privacies.open));
			
			Criterion critIsFriend = Restrictions.in("idUser", listFriends);
			Criterion critByFriend = Restrictions.and(critPrivacy, critIsFriend);
			crit.add(Restrictions.or(critByFriend, critSelf));
		} else {
			crit.add(critSelf);
		}
		System.out.println("list size friends: " + listFriends.size());
		
		crit.addOrder(Order.desc("timeUp"));
		crit.setFirstResult(offset);
		crit.setMaxResults(15);
		return (List<Status>) crit.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Status> getListStatusContainNearby(List<String> listFriends, String idUser, Double xLoc, Double yLoc, int offset) {
		final double ratio = 10; //0.0003;
		
		Criteria crit = createEntityCriteria();
		
		Criterion critBySelf = Restrictions.eq("idUser", idUser);
		
		Criterion critNearX = Restrictions.and(
				Restrictions.between("xLoc", xLoc - (offset+1)*ratio, xLoc +  (offset+1)*ratio),
				Restrictions.between("yLoc", yLoc - (offset+1)*ratio, yLoc + (offset+1)*ratio));
		Criterion critNearPrivacy = Restrictions.eq("privacy", Privacies.open);
		Criterion critByNearby = Restrictions.and(critNearPrivacy, critNearX);
		if(listFriends.size() > 0) {
			Criterion critFriendPrivacy = Restrictions.or(
					Restrictions.eq("privacy", Privacies.normal), 
					Restrictions.eq("privacy", Privacies.open));
			Criterion critByListFriend = Restrictions.in("idUser", listFriends);
			Criterion critByFriend = Restrictions.and(critFriendPrivacy, critByListFriend);
			
			crit.add(Restrictions.or(critBySelf, critByFriend, critByNearby));
		} else {
			crit.add(Restrictions.or(critBySelf, critByNearby));
		}
		
		crit.addOrder(Order.desc("timeUp"));
		crit.setFirstResult(offset);
		crit.setMaxResults(15);

		return (List<Status>) crit.list();
	}

	public Status getStatus(String idStatus) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("idStatus", idStatus));
		return (Status) crit.uniqueResult();
	}
}
