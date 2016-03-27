package vn.whoever.mainserver.dao;

import vn.whoever.mainserver.model.Users;

public interface UserDao {
	
	Users findBySsoId(String ssoId);
}
