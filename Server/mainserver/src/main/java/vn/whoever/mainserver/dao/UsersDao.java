package vn.whoever.mainserver.dao;

import vn.whoever.mainserver.model.Users;
import vn.whoever.support.model.utils.Location;

public interface UsersDao {
	
	public Users findBySsoId(String ssoId);
	public String findIdUser(String ssoId);
	public boolean checkIsOnline(String ssoId);
	
	public void registerUser(Users users);
	public void updateUser(Users users);
	public void destroyUser(String ssoId);
	
	public void updateLocation(String idUser, Location location);
	
}
