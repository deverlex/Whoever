package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.UsersDao;
import vn.whoever.mainserver.model.UserRoles;
import vn.whoever.mainserver.model.UserState;
import vn.whoever.mainserver.model.Users;

@Repository("userDao")
public class UsersDaoImpl extends AbstractDao<Integer,Users> implements UsersDao, Serializable {

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
	
	
}