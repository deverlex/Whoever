package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.CommentsDao;
import vn.whoever.mainserver.model.Comments;

public class CommentsDaoImpl extends AbstractDao<String, Comments> implements CommentsDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 185849493749L;

	public void createComment(Comments comment) {
		persist(comment);
	}

	public void updateComment(Comments comment) {
		update(comment);
	}

	@SuppressWarnings("unchecked")
	public List<Comments> getComments(String idStatus) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("idStatus", idStatus));
		return (List<Comments>) crit.list();
	}

	public void deleteComment(String idComment) {
		String sql = "delete from Comments where idComment = '" + idComment + "'";
		Query query = getSession().createQuery(sql);
		query.executeUpdate();
	}

}
