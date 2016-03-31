package vn.whoever.mainserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.whoever.mainserver.dao.UsersDao;
import vn.whoever.mainserver.model.Users;
import vn.whoever.mainserver.service.UsersService;
import vn.whoever.support.model.utils.Roles;
import vn.whoever.support.model.utils.States;

@Service("usersService")
@Transactional
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersDao dao;

	public Users findBySso(String ssoId) {
		return dao.findBySsoId(ssoId);
	}

	public void registerUser(Users users) {
//		List<UserRoles> roles = new ArrayList<UserRoles>();
//		roles.add(new UserRoles(users, Roles.ANONYMOUS));
//		roles.add(new UserRoles(users, Roles.USER));
//		UserState state = new UserState(users.getIdUser(), States.ACTIVE);
//		users.setRoles(roles);
//		users.setState(state);
//		dao.registerUser(users);
	}

	public void addRole(Users users, Roles roles) {
		
	}

	public void updateState(Users users, States state) {
		
	}
	
}