package vn.whoever.mainserver.service;

import java.util.Date;

import vn.whoever.mainserver.model.Users;

public interface AuthenticalToken {
	
	String getToken(String ssoId);
	String getToken(String ssoId, Date exp_date);
	boolean validate(String token);
	Users getUserFromToken(String token);
}
