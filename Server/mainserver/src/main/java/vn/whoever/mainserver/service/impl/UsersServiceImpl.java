package vn.whoever.mainserver.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import v.whoever.service.impl.GenerateIdImpl;
import v.whoever.service.impl.GenerateSsoIdImpl;
import vn.whoever.mainserver.dao.SetRolesDao;
import vn.whoever.mainserver.dao.TokensDao;
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
	@Qualifier("whoeverAuthenticationManager")
	protected AuthenticationManager authManager;

	@Autowired
	private UsersDao userDao;
	
	@Autowired
	private SetRolesDao roleDao;
	
	@Autowired
	private TokensDao tokenDao;
	
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
	
	public Users findByIdUser(String idUser) {
		return userDao.findByIdUser(idUser);
	}

	public void registerUser(Users users) {
		Set<SetRoles> roles = new HashSet<SetRoles>();
		roles.add(new SetRoles(users, Roles.USER));
		
		userDao.registerUser(users);
		roleDao.addRole(roles);
		contactService.createContact(users.getIdUser());
	}

	public void addRole(Users users, Roles roles) {
		
	}

	public void updateState(Users users, States state) {
		
	}

	public String findIdUser(String ssoId) {
		return userDao.findIdUser(ssoId);
	}

	public String findSsoIdbyIdUser(String idUser) {
		return userDao.findSsoId(idUser);
	}

	public List<Users> queryIdUserBySsoId(String querySsoId) {
		return userDao.queryUserBySsoId(querySsoId);
	}

	public void updateTimeUp(String idUser) {
		userDao.updateTimeUp(idUser);
	}

	public void authenticalUser(HttpServletRequest request, HttpSession session, String ssoId, String password) {
		session.invalidate();
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(ssoId, password);
		request.getSession();
		authToken.setDetails(new WebAuthenticationDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(authToken));
	}

	public void authByRequest(HttpServletRequest request) {
		String token = request.getHeader("Whoever-token");
		Users user = userDao.findByIdUser(tokenDao.getTokenByToken(token).getIdUser());
		System.out.println("idUser: " + user.getSsoId());
		System.out.println("password: " + user.getPassword());
		HttpSession session = request.getSession();
		authenticalUser(request, session, user.getSsoId(), user.getPassword());
	}
}