package vn.whoever.mainserver.service;

import java.util.Date;

import vn.whoever.mainserver.model.Tokens;
import vn.whoever.mainserver.model.Users;

public interface AuthenticalToken {
	
	/**
	 * generate a token key
	 * @param ssoId, exp
	 * @return
	 */
	public String initToken(Users users);
	public String initToken(Users users, Date timeExp);
	
	public Tokens getToken(String ssoId);
	
	public boolean validate(String token);
	
	public Users getUserFromToken(String token);

	public String getUpdateToken(String oldToken, String timeExp);
	
	public String getTimeExpiration();
}
