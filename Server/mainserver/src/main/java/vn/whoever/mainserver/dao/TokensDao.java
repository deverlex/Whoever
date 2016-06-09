package vn.whoever.mainserver.dao;

import vn.whoever.mainserver.model.Tokens;

public interface TokensDao {

	public void insertToken(Tokens tokens);

	public void updateToken(Tokens tokens);

	public boolean validateToken(String token);

	public Tokens getTokenByToken(String token);

	public Tokens getTokenByIdUser(String idUser);
}
