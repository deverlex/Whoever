package vn.whoever.mainserver.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.UserDao;
import vn.whoever.mainserver.model.Users;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<String, Users> implements UserDao {

	public Users findBySso(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		return (Users) crit.uniqueResult();
	}	
}