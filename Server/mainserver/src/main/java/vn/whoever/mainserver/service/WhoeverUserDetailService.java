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

@Service("whoeverUserDetailsService")
public class WhoeverUserDetailService implements UserDetailsService {

	@Autowired
	private UsersService usersService;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
		Users user = usersService.findBySsoId(ssoId);
		if (user == null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		return new User(user.getSsoId(), user.getPassword(), user.getState().getState().equals("active"), true, true,
				true, getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(Users user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (SetRoles role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoles()));
		}
		return authorities;
	}

}

