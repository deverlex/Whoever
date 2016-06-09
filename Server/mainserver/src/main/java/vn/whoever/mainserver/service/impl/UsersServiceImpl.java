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

import vn.whoever.mainserver.dao.SetRolesDao;
import vn.whoever.mainserver.dao.TokensDao;
import vn.whoever.mainserver.dao.UsersDao;
import vn.whoever.mainserver.model.SetRoles;
import vn.whoever.mainserver.model.Users;
import vn.whoever.mainserver.service.ContactsService;
import vn.whoever.mainserver.service.UsersService;
import vn.whoever.service.impl.GenerateIdImpl;
import vn.whoever.service.impl.GenerateSsoIdImpl;
import vn.whoever.support.model.utils.Roles;
import vn.whoever.support.model.utils.States;

/**
 * For interact with user, role, token, contact
 * @author Nguyen Van Do
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
	
	/**
	 * Method have function register more role for user, but
	 * in this source code that make one role user
	 */
	public void registerUser(Users users) {
		Set<SetRoles> roles = new HashSet<SetRoles>();
		roles.add(new SetRoles(users, Roles.USER));
		
		userDao.registerUser(users);
		roleDao.addRole(roles);
		contactService.createContact(users.getIdUser());
	}

	//This method isn't complete
	public void addRole(Users users, Roles roles) {}
	public void updateState(Users users, States state) {}

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
	
	/**
	 * This method is authenticated by id and pass when user login fist
	 */
	public void authenticalUser(HttpServletRequest request, HttpSession session, String ssoId, String password) {
		session.invalidate();
		// Invalid user. If login failed -> throw an exception (catch exception from wrapper class call this method)
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(ssoId, password);
		request.getSession();
		authToken.setDetails(new WebAuthenticationDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(authToken));
	}

	/**
	 * This method is authenticated by token in HTTP header request
	 */
	public void authByRequest(HttpServletRequest request) {
		String token = request.getHeader("Whoever-token");
		// Get user from token id
		Users user = userDao.findByIdUser(tokenDao.getTokenByToken(token).getIdUser());
		HttpSession session = request.getSession();
		// Grant access for user
		authenticalUser(request, session, user.getSsoId(), user.getPassword());
	}
}