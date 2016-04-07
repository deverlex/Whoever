package vn.whoever.mainserver.service;

import vn.whoever.mainserver.model.Users;
import vn.whoever.support.model.utils.Roles;
import vn.whoever.support.model.utils.States;

public interface UsersService {

	public String generateUserId();
	public String generateSsoId();
	public String generatePassword();
	public Users findBySsoId(String ssoId);
	public void registerUser(Users users);
	public void addRole(Users users, Roles roles);
	public void updateState(Users users, States state);
}
