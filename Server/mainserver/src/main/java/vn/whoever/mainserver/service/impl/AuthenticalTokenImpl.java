package vn.whoever.mainserver.service.impl;

import java.util.Date;

import vn.whoever.mainserver.model.Users;
import vn.whoever.mainserver.service.AuthenticalToken;

public class AuthenticalTokenImpl implements AuthenticalToken {

	public String getToken(String ssoId) {
		// TODO Auto-generated method stub
		return "";
	}

	public String getToken(String ssoId, Date exp_date) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean validate(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	public Users getUserFromToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
