package vn.whoever.mainserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.whoever.mainserver.dao.UserDao;
import vn.whoever.mainserver.model.Users;
import vn.whoever.mainserver.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;

	public Users findBySso(String ssoId) {
		return dao.findBySsoId(ssoId);
	}

	public boolean registerUser(Users users) {
		
		return dao.registerUser(users);
	}

	private boolean updateRole(Users users) {
		
		return true;
	}
	
	private boolean updateState(Users users) {
		
		return true;
	}
}