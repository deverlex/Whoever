package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.TokensDao;
import vn.whoever.mainserver.model.Tokens;

@Repository("tokenDao")
public class TokensDaoImpl extends AbstractDao<String, Tokens> implements TokensDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1488373644738220L;

	public void insertToken(Tokens tokens) {
		persist(tokens);
	}

	public void updateToken(Tokens tokens) {
		persist(tokens);
	}

	public boolean validateToken(String token) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("token", token));
		return crit.uniqueResult() != null;
	}

	public Tokens getTokens(String token) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("token", token));
		return (Tokens) crit.uniqueResult();
	}


}
