package vn.whoever.mainsite.service;

import vn.whoever.mainsite.model.User;

public interface UserService {
	
	public User findBySsoId(String ssoId);
}
