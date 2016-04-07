package vn.whoever.mainserver.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v.whoever.service.impl.GenerateTokenImpl;
import vn.whoever.mainserver.dao.TokensDao;
import vn.whoever.mainserver.model.Tokens;
import vn.whoever.mainserver.model.Users;
import vn.whoever.mainserver.service.AuthenticalToken;

@Service("tokenService")
@Transactional
public class AuthenticalTokenImpl implements AuthenticalToken {

	/**
	 * Get token when login => saved to DB
	 */
	
	@Autowired
	private TokensDao tokensDao;
	
	public String getToken(Users users) {
		String token = GenerateTokenImpl.getToken().getTokenId(users.getSsoId());
		Tokens tokens = new Tokens(users, token);
		tokensDao.insertToken(tokens);
		return token;
	}

	/**
	 * Get token and set time expiration for token
	 */
	public String getToken(Users users, Date timeExp) {
		String token = GenerateTokenImpl.getToken().getTokenId(users.getSsoId());
		Tokens tokens = new Tokens(users, token);
		tokens.setTimeExp(timeExp);
		tokensDao.insertToken(tokens);
		return token;
	}

	/**
	 * validate token
	 */
	public boolean validate(String token) {
		return tokensDao.validateToken(token);
	}

	public Users getUserFromToken(String token) {
		return tokensDao.getTokens(token).getUsers();
	}

	public String getUpdateToken(String oldToken, Date timeExp) {
		Tokens tokens = tokensDao.getTokens(oldToken);
		String newToken = GenerateTokenImpl.getToken().getTokenId(tokens.getUsers().getSsoId());
		tokens.setTimeExp(timeExp);
		tokens.setToken(newToken);
		tokensDao.updateToken(tokens);
		return newToken;
	}
	
}
