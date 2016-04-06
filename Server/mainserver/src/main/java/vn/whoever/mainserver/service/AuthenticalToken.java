package vn.whoever.mainserver.service;

import java.util.Date;

import vn.whoever.mainserver.model.Users;

public interface AuthenticalToken {
	
	/**
	 * generate a token key
	 * @param ssoId, exp
	 * @return
	 */
	public String getToken(String ssoId);
	public String getToken(String ssoId, Date timeExp);
	
	/**
	 * check token in database
	 * @param token
	 * @return
	 */
	public boolean validate(String token);
	
	/**
	 * get user information from token key
	 * @param token
	 * @return
	 */
	public Users getUserFromToken(String token);
}
