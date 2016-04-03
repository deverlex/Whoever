package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;

import org.hibernate.Criteria;
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

	public void registerUser(Users users) {	
		persist(users);
	}

	public void updateUser(Users users) {
	
	}

	public void destroyUser(String ssoId) {
		
	}

	public String findIdUser(String ssoId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean checkIsOnline(String ssoId) {
		// TODO Auto-generated method stub
		return false;
	}

	public void updateLocation(String idUser, Location location) {
		// TODO Auto-generated method stub
		
	}
	
	
}