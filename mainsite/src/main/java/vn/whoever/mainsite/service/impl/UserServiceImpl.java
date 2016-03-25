package vn.whoever.mainsite.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import vn.whoever.mainsite.dao.UserDao;
import vn.whoever.mainsite.model.Users;
import vn.whoever.mainsite.service.UserService;

public class UserServiceImpl implements UserService {

	@Autowired
	UserDao dao;
	
	public Users findBySsoId(String ssoId) {
		return dao.findBySsoId(ssoId);
	}

}
