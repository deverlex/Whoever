package vn.whoever.mainsite.dao;

import vn.whoever.mainsite.model.UserStates;
import vn.whoever.mainsite.model.Users;

public interface UserDao {

	public Users findBySsoId(String ssoId);
	//public boolean createNewUser(User user, );
	
}
