package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.UsersDao;
import vn.whoever.mainserver.model.Users;
import vn.whoever.support.model.utils.Location;
/**
 * @author Nguyen Van Do
 *	
 *	This class provide accessing to database that concern about users.
 */
@Repository("userDao")
public class UsersDaoImpl extends AbstractDao<String, Users> implements UsersDao, Serializable {

	private static final long serialVersionUID = 189797394999L;

	public Users findBySsoId(String ssoId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", ssoId));
		return (Users) crit.uniqueResult();
	}
	
	public Users findByIdUser(String idUser) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("idUser", idUser));
		return (Users) crit.uniqueResult();
	}
	
	public String findIdUser(String ssoId) {
		String sql = "select idUser from Users where ssoId = '" + ssoId + "'";
		Query query = getSession().createQuery(sql);
		return (String) query.uniqueResult();
	}
	
	// This method isn't complete at now
	public boolean checkIsOnline(String ssoId) {
		return false;
	}

	public void registerUser(Users users) {
		persist(users);
	}

	public void updateUser(Users users) {
		persist(users);
	}

	public void destroyUser(String ssoId) {
		String sql = "delete from Users where ssoId = '" + ssoId + "'";
		Query query = getSession().createQuery(sql);
		query.executeUpdate();
	}

	// This method isn't complete
	public void updateLocation(String idUser, Location location) {}

	public String findSsoId(String idUser) {
		String sql = "select ssoId from Users where idUser = '" + idUser + "'";
		Query query = getSession().createQuery(sql);
		return (String) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Users> queryUserBySsoId(String querySsoId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.like("ssoId", querySsoId, MatchMode.ANYWHERE));
		return (List<Users>) crit.list();
	}

	public void updateTimeUp(String idUser) {
		String sql = "update Users set timeUp = now() where idUser = '" + idUser + "'";
		getSession().createQuery(sql).executeUpdate();
	}
}