package vn.whoever.mainserver.service;

import vn.whoever.mainserver.model.Users;

public interface UserService {

	public Users findBySso(String sso);
	public boolean registerUser(Users users);
}
