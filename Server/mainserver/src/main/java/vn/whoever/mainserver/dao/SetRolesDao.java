package vn.whoever.mainserver.dao;

import java.util.List;
import java.util.Set;

import vn.whoever.mainserver.model.SetRoles;
import vn.whoever.support.model.utils.Roles;

public interface SetRolesDao {

	public void addRole(Set<SetRoles> roles);
	public void deleteRole(long idUser);
	public long getIdRole(long idUser, String role);
	public List<Roles> getListRole(long idUser);
}
