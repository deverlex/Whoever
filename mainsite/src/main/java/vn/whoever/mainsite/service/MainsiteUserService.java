package vn.whoever.mainsite.service;

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

import vn.whoever.mainsite.model.UserRoles;
import vn.whoever.mainsite.model.Users;

@Service(value = "mainsiteUserService")
public class MainsiteUserService implements UserDetailsService {

	@Autowired
	UserService userService;
	
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {

		Users users = userService.findBySsoId(ssoId);
		System.out.println("User: " + users);
		if(users == null) {
			System.out.println("User is not founded!!!");
			throw new UsernameNotFoundException("Not found user!!");
		}
		return buildUserForAuthentication(users, buildGrantedAuthorities(users));
	} 
	
	private User buildUserForAuthentication(Users users, List<GrantedAuthority> authorities) {
		return new User(users.getSsoId(), users.getPassword(), 
				users.getStates().getState().equals("active"), true, true, true, authorities);
	}
	
	private List<GrantedAuthority> buildGrantedAuthorities(Users users) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(UserRoles roles : users.getRoles()) {
			System.out.println(roles);
			authorities.add(new SimpleGrantedAuthority("ROLE_" + roles.getRole()));
		}
		System.out.println("author: " + authorities);
		return authorities;
	}

}
