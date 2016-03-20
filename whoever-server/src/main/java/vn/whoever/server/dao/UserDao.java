package vn.whoever.server.dao;

public interface UserDao {

	String getRoleUser(String username);
	boolean validateUser(String username, String password);
	
}
