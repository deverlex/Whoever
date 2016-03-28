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
import vn.whoever.mainserver.dao.UserDao;
import vn.whoever.mainserver.model.UserRoles;
import vn.whoever.mainserver.model.UserStates;
import vn.whoever.mainserver.model.Users;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer,Users> implements UserDao, Serializable {

	private static final long serialVersionUID = 189797394999L;

	public Users findBySsoId(String ssoId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", ssoId));
		return (Users) crit.uniqueResult();
	}

	public boolean registerUser(Users users) {
		
		return false;
	}

	public boolean updateUser(Users users) {
	
		return false;
	}

	public boolean destroyUser(String ssoId) {
	
		return false;
	}
	
	
}