package vn.whoever.mainserver.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v.whoever.service.impl.GenerateTokenImpl;
import vn.whoever.mainserver.dao.TokensDao;
import vn.whoever.mainserver.dao.UsersDao;
import vn.whoever.mainserver.model.Tokens;
import vn.whoever.mainserver.model.Users;
import vn.whoever.mainserver.service.AuthenticalToken;
import vn.whoever.support.utils.FormatDate;

@Service("tokenService")
@Transactional
public class AuthenticalTokenImpl implements AuthenticalToken {

	/**
	 * Get token when login => saved to DB
	 */

	@Autowired
	private TokensDao tokensDao;
	
	@Autowired
	private UsersDao usersDao;

	public String initToken(Users users) {
		String token = GenerateTokenImpl.getToken().getTokenId(users.getSsoId());
		Tokens tokens = new Tokens(users, token);
		tokensDao.insertToken(tokens);
		return token;
	}

	public String initToken(Users users, Date timeExp) {
		String token = GenerateTokenImpl.getToken().getTokenId(users.getSsoId());
		Tokens tokens = new Tokens(users, token);
		tokens.setTimeExp(timeExp);
		tokensDao.insertToken(tokens);
		return token;
	}
	
	public Tokens getToken(String ssoId) {
		Users users = usersDao.findBySsoId(ssoId);
		Tokens tokens = tokensDao.getTokenByIdUser(users.getIdUser());
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

	public Users getUserFromToken(String token) {
		return tokensDao.getTokenByToken(token).getUsers();
	}

	public String getUpdateToken(String oldToken, String timeExp) {
		Tokens tokens = tokensDao.getTokenByToken(oldToken);
		String newToken = GenerateTokenImpl.getToken().getTokenId(tokens.getUsers().getSsoId());
		tokens.setTimeExp(timeExp);
		tokens.setToken(newToken);
		tokensDao.updateToken(tokens);
		return newToken;
	}

	/**
	 * get time after 1 day
	 * 
	 */
	public String getTimeExpiration() {
		long newTimeExp = (new Date()).getTime() + 24 * 60 * 60 * 1000;
		return (new FormatDate(newTimeExp)).toDateString();
	}
}
