package vn.whoever.mainserver.dao;

import vn.whoever.mainserver.model.UserRoles;

public interface UserRolesDao {

	public void addRole(UserRoles roles);
	public void deleteRole(long idRole);
	public long getIdRole(long idUser, String role);
}
