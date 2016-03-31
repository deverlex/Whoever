package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.UserRolesDao;
import vn.whoever.mainserver.model.UserRoles;
import vn.whoever.support.model.utils.Roles;

@Repository("roleDao")
public class UserRolesDaoImpl extends AbstractDao<String, UserRoles> implements UserRolesDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -14356542223L;

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

	public List<Roles> getListRole(long idUser) {
		// TODO Auto-generated method stub
		return null;
	}

}
