package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.StatusUserDao;
import vn.whoever.mainserver.model.StatusUsers;
import vn.whoever.support.model.utils.Interacts;
/**
 * @author Nguyen Van Do
 *	
 *	This class provide accessing to database that concern about linked between status and user.
 */
@Repository("statusUsersDao")
public class StatusUsersDaoImpl extends AbstractDao<String, StatusUsers> implements StatusUserDao, Serializable {

	private static final long serialVersionUID = 165687654369903L;

	public void addInteractStatus(String idStatus, String idUser, Interacts interact) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.and(Restrictions.eq("idStatus", idStatus), Restrictions.eq("idUser", idUser)));
		// Get linked between status and user
		StatusUsers statusUsers = (StatusUsers) crit.uniqueResult();
		// If linked available => change interaction of user on status
		if (statusUsers != null) {
			if (statusUsers.getInteract().equals(interact)) {
				statusUsers.setInteract(Interacts.normal);
			} else {
				statusUsers.setInteract(interact);
			}
			updateInteract(statusUsers);
		} else {
			// create a new linked between status anf user.
			statusUsers = new StatusUsers(idStatus, idUser, interact);
			createInteract(statusUsers);
		}
	}

	public Interacts getInteractStateStatus(String idStatus, String idUser) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.and(Restrictions.eq("idStatus", idStatus), Restrictions.eq("idUser", idUser)));
		// Get linked between status and user
		StatusUsers statusUsers = (StatusUsers) crit.uniqueResult();
		// If linked available => return interaction between status and user
		if (statusUsers != null) {
			return statusUsers.getInteract();
		}
		// Else return default value of interaction
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
		crit.add(Restrictions.and(Restrictions.eq("idStatus", idStatus), Restrictions.eq("interact", interact)));
		crit.setProjection(Projections.rowCount());
		return ((Number) crit.uniqueResult()).intValue();
	}
}
