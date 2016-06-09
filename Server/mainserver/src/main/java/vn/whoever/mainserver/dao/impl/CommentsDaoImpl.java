package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.CommentsDao;
import vn.whoever.mainserver.model.Comments;
/**
 * @author Nguyen Van Do
 *	
 *	This class provide accessing to database that concern about status's comments.
 */
@Repository
public class CommentsDaoImpl extends AbstractDao<String, Comments> implements CommentsDao, Serializable {

	private static final long serialVersionUID = 185849493749L;

	public void createComment(Comments comment) {
		persist(comment);
	}

	public void updateComment(Comments comment) {
		update(comment);
	}

	@SuppressWarnings("unchecked")
	public List<Comments> getListComments(String idStatus) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("idStatus", idStatus));
		
		crit.addOrder(Order.desc("timePost"));
		crit.setMaxResults(15);
		return (List<Comments>) crit.list();
	}

	public void deleteComment(String idComment) {
		String sql = "delete from Comments where idComment = '" + idComment + "'";
		Query query = getSession().createQuery(sql);
		query.executeUpdate();
	}

	public int getTotalCommentStatus(String idStatus) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("idStatus", idStatus));
		crit.setProjection(Projections.rowCount());
		return ((Number) crit.uniqueResult()).intValue();
	}
}
