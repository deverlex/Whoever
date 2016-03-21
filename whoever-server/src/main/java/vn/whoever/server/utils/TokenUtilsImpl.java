package vn.whoever.server.utils;

import vn.whoever.server.entity.User;

public class TokenUtilsImpl implements TokenUtils {

	public String getToken(User user) {
		
		return "tokendemo";
	}

	public String getToken(User user, Long experation) {
		
		return "tokendemo";
	}

	public boolean validate(String token) {
		
		return true;
	}

	public User getUserFromToken(String token) {
		return new User("nguyendo", "1903", "ROLE_USER");
	}
	
	
}
