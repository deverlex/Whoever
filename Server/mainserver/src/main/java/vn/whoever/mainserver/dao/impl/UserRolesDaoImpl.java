package vn.whoever.mainserver.dao.impl;

import org.springframework.stereotype.Repository;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.UserRolesDao;
import vn.whoever.mainserver.model.UserRoles;

@Repository("roleDao")
public class UserRolesDaoImpl extends AbstractDao<Long, UserRoles> implements UserRolesDao {

	public void addRole(UserRoles roles) {
		persist(roles);
	}

	public void deleteRole(long idRole) {
		// TODO Auto-generated method stub
		
	}

	public long getIdRole(long idUser, String role) {
		// TODO Auto-generated method stub
		return 0;
	}

}
