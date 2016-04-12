package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.UsersDao;
import vn.whoever.mainserver.model.Users;
import vn.whoever.support.model.utils.Location;


@Repository("userDao")
public class UsersDaoImpl extends AbstractDao<String, Users> implements UsersDao, Serializable {

	private static final long serialVersionUID = 189797394999L;

	public Users findBySsoId(String ssoId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", ssoId));
		return (Users) crit.uniqueResult();
	}
	
	public String findIdUser(String ssoId) {
		String sql = "select idUser from Users where ssoId = '" + ssoId + "'";
		Query query = getSession().createQuery(sql);
		return (String) query.uniqueResult();
	}
	
	public boolean checkIsOnline(String ssoId) {
		// TODO Auto-generated method stub
		
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

	public void updateLocation(String idUser, Location location) {
		// TODO Auto-generated method stub
	}

	public String findSsoId(String idUser) {
		String sql = "select ssoId from Users where idUser = '" + idUser + "'";
		Query query = getSession().createQuery(sql);
		return (String) query.uniqueResult();
	}
}