package vn.whoever.mainserver.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import v.whoever.service.impl.GenerateIdImpl;
import v.whoever.service.impl.GenerateSsoIdImpl;
import vn.whoever.mainserver.dao.SetRolesDao;
import vn.whoever.mainserver.dao.UsersDao;
import vn.whoever.mainserver.model.SetRoles;
import vn.whoever.mainserver.model.Users;
import vn.whoever.mainserver.service.UsersService;
import vn.whoever.support.model.utils.Roles;
import vn.whoever.support.model.utils.States;

@Service("usersService")
@Transactional
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersDao dao;
	
	@Autowired
	private SetRolesDao roleDao;
	
	public String generateUserId() {
		return GenerateIdImpl.getId().getIdUser();
	}
	
	public String generateSsoId() {
		return GenerateSsoIdImpl.getId().getSsoId();
	}

	public Users findBySso(String ssoId) {
		return dao.findBySsoId(ssoId);
	}

	public void registerUser(Users users) {
		Set<SetRoles> roles = new HashSet<SetRoles>();
		roles.add(new SetRoles(users, Roles.USER));
		dao.registerUser(users);
		roleDao.addRole(roles);
	}

	public void addRole(Users users, Roles roles) {
		
	}

	public void updateState(Users users, States state) {
		
	}
	
}