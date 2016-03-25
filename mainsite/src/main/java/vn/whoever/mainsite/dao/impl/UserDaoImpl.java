package vn.whoever.mainsite.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import vn.whoever.mainsite.dao.AbstractDao;
import vn.whoever.mainsite.dao.UserDao;
import vn.whoever.mainsite.model.Users;

public class UserDaoImpl extends AbstractDao<String, Users> implements UserDao {

	public Users findBySsoId(String ssoId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("ssoId", ssoId));
		return (Users) criteria.uniqueResult();
	}
}
