package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.SetRolesDao;
import vn.whoever.mainserver.model.SetRoles;
import vn.whoever.support.model.utils.Roles;

@Repository("roleDao")
public class SetRolesDaoImpl extends AbstractDao<String, SetRoles> implements SetRolesDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -14356542223L;

	public void addRole(Set<SetRoles> roles) {
		for (SetRoles role : roles) {
			persist(role);
		}
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
