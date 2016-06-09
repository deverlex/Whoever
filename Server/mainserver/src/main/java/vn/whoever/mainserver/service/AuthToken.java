package vn.whoever.mainserver.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import vn.whoever.mainserver.model.Tokens;
import vn.whoever.mainserver.model.Users;

public interface AuthToken {

	public String initToken(Users users);

	public String initToken(Users users, Date timeExp);

	public Tokens getToken(String ssoId);

	public boolean validate(String token);

	public String getUpdateToken(String oldToken, String timeExp);

	public String getTimeExpiration();

	public String getIdUser(String token);

	public String getIdUserHttp(HttpServletRequest request);
}
