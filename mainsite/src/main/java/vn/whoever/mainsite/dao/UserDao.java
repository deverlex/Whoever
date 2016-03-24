package vn.whoever.mainsite.dao;

import vn.whoever.mainsite.model.State;
import vn.whoever.mainsite.model.User;

public interface UserDao {

	public User findBySsoId(String ssoId);
	//public boolean createNewUser(User user, );
	
}
