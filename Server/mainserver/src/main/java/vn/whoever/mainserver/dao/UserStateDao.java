package vn.whoever.mainserver.dao;

import java.lang.Thread.State;

import vn.whoever.mainserver.model.UserState;

public interface UserStateDao {

	public void addState(UserState state);
	public void updateState(UserState state);
	public void deleteState(long idUser);
	public State findState(long idUser);
}
