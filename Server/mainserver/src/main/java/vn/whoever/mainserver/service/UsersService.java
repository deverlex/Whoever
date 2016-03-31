package vn.whoever.mainserver.service;

import vn.whoever.mainserver.model.Users;
import vn.whoever.support.model.utils.Roles;
import vn.whoever.support.model.utils.States;

public interface UsersService {

	public Users findBySso(String sso);
	public void registerUser(Users users);
	public void addRole(Users users, Roles roles);
	public void updateState(Users users, States state);
}
