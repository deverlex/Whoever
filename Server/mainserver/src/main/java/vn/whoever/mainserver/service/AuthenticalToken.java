package vn.whoever.mainserver.service;

import java.util.Date;

import vn.whoever.mainserver.model.Users;

public interface AuthenticalToken {
	
	/**
	 * generate a token key
	 * @param ssoId, exp
	 * @return
	 */
	public String getToken(Users users);
	public String getToken(Users users, Date timeExp);
	
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
	
	/**
	 * update new token when it have expired time
	 * @param oldToken
	 * @param oldTimeExp
	 * @return
	 */
	public String getUpdateToken(String oldToken, Date timeExp);
}
