package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.CommentUserDao;
import vn.whoever.mainserver.model.CommentUsers;
import vn.whoever.support.model.utils.Interacts;

@Repository
public class CommentUserDaoImpl extends AbstractDao<String, CommentUsers> implements CommentUserDao, Serializable {

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1899383762204L;

	public void addInteractComment(String idComment, String idUser, Interacts interact) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.and(Restrictions.eq("idComment", idComment), 
				Restrictions.eq("idUser", idUser)));
		CommentUsers commentUsers = (CommentUsers) crit.uniqueResult();
		if(commentUsers != null) {
			if(commentUsers.getInteracts().equals(interact)) {
				commentUsers.setInteracts(Interacts.normal);
				update(commentUsers);
			} else {
				commentUsers.setInteracts(interact);
				update(commentUsers);
			}
		} else {
			commentUsers = new CommentUsers(idComment, idUser, interact);
			persist(commentUsers);
		}
	}

	public Interacts getInteractStateComment(String idComment, String idUser) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.and(Restrictions.eq("idComment", idComment),
				Restrictions.eq("idUser", idUser)));
		CommentUsers commentUsers = (CommentUsers) crit.uniqueResult();
		if(commentUsers != null) {
			return commentUsers.getInteracts();
		}
		return Interacts.normal;
	}

	public int getTotalInteract(String idComment, Interacts interact) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.and(Restrictions.eq("idComment", idComment), 
				Restrictions.eq("interact", interact)));
		crit.setProjection(Projections.rowCount());
		return ((Number) crit.uniqueResult()).intValue();
	}
}
