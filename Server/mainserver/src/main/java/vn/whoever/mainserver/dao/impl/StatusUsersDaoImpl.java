package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.annotations.Entity;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.StatusUserDao;
import vn.whoever.mainserver.model.StatusUsers;
import vn.whoever.support.model.utils.Interacts;

@Repository("statusUsersDao")
public class StatusUsersDaoImpl extends AbstractDao<String, StatusUsers> implements StatusUserDao,Serializable {

	/**
	 * 
	 * 
	 */
	
	private static final long serialVersionUID = 165687654369903L;

	public void addInteractStatus(String idStatus, String idUser, Interacts interact) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.and(
				Restrictions.eq("idStatus", idStatus), 
				Restrictions.eq("idUser", idUser)));
		StatusUsers statusUsers = (StatusUsers) crit.uniqueResult();
		if(statusUsers != null) {
			if(statusUsers.getInteract().equals(interact)) {
				statusUsers.setInteract(Interacts.normal);
			} else {
				statusUsers.setInteract(interact);
			}
			updateInteract(statusUsers);
		} else {
			statusUsers = new StatusUsers(idStatus, idUser, interact);
			createInteract(statusUsers);
		}
	}
	
	public Interacts getInteractStateStatus(String idStatus, String idUser) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.and(Restrictions.eq("idStatus", idStatus), 
				Restrictions.eq("idUser", idUser)));
		StatusUsers statusUsers = (StatusUsers) crit.uniqueResult();
		if(statusUsers != null) {
			return statusUsers.getInteract();
		}
		return Interacts.normal;
	}
	
	private void updateInteract(StatusUsers statusUsers) {
		update(statusUsers);
	}
	
	private void createInteract(StatusUsers statusUsers) {
		persist(statusUsers);
	}

	public int getTotalInteract(String idStatus, Interacts interact) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.and(Restrictions.eq("idStatus", idStatus), 
				Restrictions.eq("interact", interact)));
		crit.setProjection(Projections.rowCount());
		return ((Number) crit.uniqueResult()).intValue();
	}

}
