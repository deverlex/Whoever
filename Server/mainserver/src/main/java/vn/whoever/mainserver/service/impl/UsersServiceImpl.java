package vn.whoever.mainserver.service.impl;

import java.lang.Thread.State;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.whoever.mainserver.dao.UsersDao;
import vn.whoever.mainserver.model.Users;
import vn.whoever.mainserver.service.UsersService;
import vn.whoever.support.model.utils.Roles;

@Service("usersService")
@Transactional
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersDao dao;

	public Users findBySso(String ssoId) {
		return dao.findBySsoId(ssoId);
	}

	public void registerUser(Users users) {
		dao.registerUser(users);
	}

	public void addRole(Users users, Roles roles) {
		
	}

	public void updateState(Users users, State state) {
		// TODO Auto-generated method stub
		
	}
	
}