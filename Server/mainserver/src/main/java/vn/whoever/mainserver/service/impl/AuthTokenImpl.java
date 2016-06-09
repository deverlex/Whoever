package vn.whoever.mainserver.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.whoever.mainserver.dao.TokensDao;
import vn.whoever.mainserver.dao.UsersDao;
import vn.whoever.mainserver.model.Tokens;
import vn.whoever.mainserver.model.Users;
import vn.whoever.mainserver.service.AuthToken;
import vn.whoever.service.impl.GenerateTokenImpl;
import vn.whoever.support.utils.FormatDate;
/**
 * @author Nguyen Van Do
 * This service functions concerned token.
 * Example: generation, get user, get time...
 * Id user can get from token because each users have one token.
 * A token string have 32 characters - very difficult known token from without system.
 */
@Service("tokenService")
@Transactional
public class AuthTokenImpl implements AuthToken {

	@Autowired
	private TokensDao tokensDao;

	@Autowired
	private UsersDao usersDao;

	// Initialize token with time expand default
	public String initToken(Users users) {
		String token = GenerateTokenImpl.getToken().getTokenId(users.getSsoId());
		Tokens tokens = new Tokens(users.getIdUser(), token);
		tokensDao.insertToken(tokens);
		return token;
	}

	// Initialize token with time set by system admin
	public String initToken(Users users, Date timeExp) {
		String token = GenerateTokenImpl.getToken().getTokenId(users.getSsoId());
		Tokens tokens = new Tokens(users.getIdUser(), token);
		tokens.setTimeExp(timeExp);
		tokensDao.insertToken(tokens);
		return token;
	}

	public Tokens getToken(String ssoId) {
		Users users = usersDao.findBySsoId(ssoId);
		Tokens tokens = tokensDao.getTokenByIdUser(users.getIdUser());
		// if token over time expand then -> generation new token -> return to client.
		if ((new FormatDate(tokens.getTimeExp())).toDate().getTime() - (new Date()).getTime() < 0) {
			tokens.setTimeExp(getTimeExpiration());
			String token = GenerateTokenImpl.getToken().getTokenId(ssoId);
			tokens.setToken(token);
			tokensDao.updateToken(tokens);
			return tokens;
		}
		return tokens;
	}

	public boolean validate(String token) {
		return tokensDao.validateToken(token);
	}

	// This method isn't complete
	public String getUpdateToken(String oldToken, String timeExp) {
		return null;
	}
	
	// Time default for expiration is a day.
	public String getTimeExpiration() {
		long newTimeExp = (new Date()).getTime() + 24 * 60 * 60 * 1000;
		return (new FormatDate(newTimeExp)).toDateString();
	}

	public String getIdUser(String token) {
		return tokensDao.getTokenByToken(token).getIdUser();
	}

	public String getIdUserHttp(HttpServletRequest request) {
		// Get token id in header packet of HTTP message
		String token = request.getHeader("Whoever-token");
		return tokensDao.getTokenByToken(token).getIdUser();
	}
}
