package vn.whoever.mainserver.dao;

import vn.whoever.mainserver.model.Users;

public interface UsersDao {
	
	public Users findBySsoId(String ssoId);
	public void registerUser(Users users);
	public void updateUser(Users users);
	public void destroyUser(String ssoId);
}
