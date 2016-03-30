package vn.whoever.mainserver.dao.impl;

import java.io.Serializable;
import java.lang.Thread.State;

import vn.whoever.mainserver.dao.AbstractDao;
import vn.whoever.mainserver.dao.UserStateDao;
import vn.whoever.mainserver.model.UserState;

public class UserStateDaoImpl extends AbstractDao<Long, UserState> implements UserStateDao, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 123332328211L;

	public void addState(UserState state) {
		// TODO Auto-generated method stub
		
	}

	public void updateState(UserState state) {
		// TODO Auto-generated method stub
		
	}

	public void deleteState(long idUser) {
		// TODO Auto-generated method stub
		
	}

	public State findState(long idUser) {
		// TODO Auto-generated method stub
		return null;
	}	
}
