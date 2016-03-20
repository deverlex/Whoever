package vn.whoever.server.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1089833959L;
	
	public MyAuthenticationProvider() {
		super();
	}

	public boolean supports(Class<? extends Object> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}

	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		String username = auth.getName();
		String password = auth.getCredentials().toString();
		System.out.println("username: " + username);
		System.out.println("password: " + password);
		
		if(username.equals("nguyendo") && password.equals("1903")) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			Authentication authentication 
					= new UsernamePasswordAuthenticationToken(username, password, authorities);
			return authentication;
		} else {
			return null;
		}
	}

}
