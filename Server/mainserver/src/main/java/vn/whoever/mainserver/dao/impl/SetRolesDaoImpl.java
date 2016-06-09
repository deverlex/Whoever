package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.SetRolesDao;
import vn.whoever.mainserver.model.SetRoles;
import vn.whoever.support.model.utils.Roles;
/**
 * @author Nguyen Van Do
 *	
 *	This class provide accessing to database that concern about roles of user.
 */
@Repository("roleDao")
public class SetRolesDaoImpl extends AbstractDao<String, SetRoles> implements SetRolesDao, Serializable {

	private static final long serialVersionUID = -14356542223L;

	public void addRole(Set<SetRoles> roles) {
		for (SetRoles role : roles) {
			persist(role);
		}
	}
	
	// This method isn't completed.
	public void deleteRole(long idUser) {}
	
	// This method isn't completed.
	public long getIdRole(long idUser, String role) {
		return 0;
	}
	
	// This method isn't completed.
	public List<Roles> getListRole(long idUser) {
		return null;
	}
}
