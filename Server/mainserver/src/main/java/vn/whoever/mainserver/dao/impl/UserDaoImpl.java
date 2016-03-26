package vn.whoever.mainserver.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mysql.fabric.xmlrpc.base.Array;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.UserDao;
import vn.whoever.mainserver.model.UserRoles;
import vn.whoever.mainserver.model.UserStates;
import vn.whoever.mainserver.model.Users;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<String, Users> implements UserDao {

	public Users findBySso(String sso) {
//		Criteria crit = createEntityCriteria();
//		crit.add(Restrictions.eq("ssoId", sso));
//		return (Users) crit.uniqueResult();
		Users users = new Users();
		users.setSsoId("admin");
		users.setPassword("12345678");
		
		UserStates states = new UserStates();
		states.setState("active");
		
		Set<UserRoles> roles = new HashSet<UserRoles>();
		UserRoles role = new UserRoles();
		role.setRole("ADMIN");
		roles.add(role);
		
		users.setRoles(roles);
		
		users.setStates(states);
		
		return users;
	}	
}