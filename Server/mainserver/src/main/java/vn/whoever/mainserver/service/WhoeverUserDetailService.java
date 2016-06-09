package vn.whoever.mainserver.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.whoever.mainserver.model.SetRoles;
import vn.whoever.mainserver.model.Users;
/**
 * @author Nguyen Van Do
 * This class provide accessing to system for user when user login.
 */
@Service("whoeverUserDetailsService")
public class WhoeverUserDetailService implements UserDetailsService {

	@Autowired
	private UsersService usersService;

	/**
	 * This method provide info of user having ssoId.
	 * After this, web service is comparing data info of user send to server with data stored on server
	 */
	@SuppressWarnings("unused")
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
		// Get user info stored on Database
		Users user = usersService.findBySsoId(ssoId);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		return new User(user.getSsoId(), user.getPassword(), user.getState().getState().equals("active"), true, true,
				true, getGrantedAuthorities(user));
	}

	// Get granted of role user having
	private List<GrantedAuthority> getGrantedAuthorities(Users user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (SetRoles role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoles()));
		}
		return authorities;
	}
}

