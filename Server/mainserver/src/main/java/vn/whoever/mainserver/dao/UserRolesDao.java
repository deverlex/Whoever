package vn.whoever.mainserver.dao;

import java.util.List;

import vn.whoever.mainserver.model.SetRoles;
import vn.whoever.support.model.utils.Roles;

public interface UserRolesDao {

	public void addRole(SetRoles roles);
	public void deleteRole(long idRole);
	public long getIdRole(long idUser, String role);
	public List<Roles> getListRole(long idUser);
}
