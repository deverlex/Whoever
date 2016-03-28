package vn.whoever.mainserver.dao;

import vn.whoever.mainserver.model.Users;

public interface UserDao {
	
	public Users findBySsoId(String ssoId);
	public boolean registerUser(Users users);
	public boolean updateUser(Users users);
	public boolean destroyUser(String ssoId);
	
}
