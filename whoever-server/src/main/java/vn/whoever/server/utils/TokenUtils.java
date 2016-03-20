package vn.whoever.server.utils;

import vn.whoever.server.entity.User;

public interface TokenUtils {
	String getToken(User user);
	String getToken(User user, Long experation);
	boolean validate(String token);
	User getUserFromToken(String token);
}
