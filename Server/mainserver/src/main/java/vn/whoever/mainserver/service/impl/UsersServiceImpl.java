package vn.whoever.mainserver.service.impl;

import java.util.HashSet;
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
import vn.whoever.mainserver.service.ContactsService;
import vn.whoever.mainserver.service.UsersService;
import vn.whoever.support.model.utils.Roles;
import vn.whoever.support.model.utils.States;

/**
 * For interact with user, role, token, contact
 * @author spider man
 * @date 3/2016
 */

@Service("usersService")
@Transactional
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersDao userDao;
	
	@Autowired
	private SetRolesDao roleDao;
	
	@Autowired
	private ContactsService contactService;
	
	public String generateUserId() {
		return GenerateIdImpl.generateId().getId();
	}
	
	public String generateSsoId() {
		return GenerateSsoIdImpl.getId().getSsoId();
	}
	
	public String generatePassword() {
		return GenerateSsoIdImpl.getId().getPassword();
	}

	public Users findBySsoId(String ssoId) {
		return userDao.findBySsoId(ssoId);
	}

	public boolean registerUser(Users users) {
		Set<SetRoles> roles = new HashSet<SetRoles>();
		roles.add(new SetRoles(users, Roles.USER));
		
		try {
			userDao.registerUser(users);
			roleDao.addRole(roles);
			contactService.createContact(users.getIdUser());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void addRole(Users users, Roles roles) {
		
	}

	public void updateState(Users users, States state) {
		
	}
	
}