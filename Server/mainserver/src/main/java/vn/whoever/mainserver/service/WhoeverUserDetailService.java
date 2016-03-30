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

import vn.whoever.mainserver.model.UserRoles;
import vn.whoever.mainserver.model.Users;

@Service("whoeverUserDetailsService")
public class WhoeverUserDetailService implements UserDetailsService{

	@Autowired
	private UsersService usersService;
	
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
		Users user = usersService.findBySso(ssoId);
		System.out.println("User : " + user);
		if (user == null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
		System.out.println("ssoId: " + user.getSsoId());
		System.out.println("password: " + user.getPassword());
		System.out.println("isActve: " + user.getState().getState().equals("active"));
		return new User(user.getSsoId(), user.getPassword(), user.getState().getState().equals("active"), true, true, true,
				getGrantedAuthorities(user));
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(Users user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(UserRoles role : user.getRoles()){
			authorities.add(new SimpleGrantedAuthority("ROLE_"+ role.getRoles()));
		}
		System.out.print("authorities :"+authorities);
		return authorities;
	}
	
}

