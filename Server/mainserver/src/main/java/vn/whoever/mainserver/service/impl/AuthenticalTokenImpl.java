package vn.whoever.mainserver.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import vn.whoever.mainserver.model.Tokens;
import vn.whoever.mainserver.model.Users;
import vn.whoever.mainserver.service.AuthenticalToken;

@Service("tokenService")
@Transactional
public class AuthenticalTokenImpl implements AuthenticalToken {

	/**
	 * Get token when login => saved to DB
	 */
	public String getToken(String ssoId) {
		return "";
	}

	/**
	 * Get token and set time expiration for token
	 */
	public String getToken(String ssoId, Date timeExp) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * validate token
	 */
	public boolean validate(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	public Users getUserFromToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
