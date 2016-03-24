package vn.whoever.mainsite.service;

import vn.whoever.mainsite.model.Users;

public interface UserService {
	
	public Users findBySsoId(String ssoId);
}
