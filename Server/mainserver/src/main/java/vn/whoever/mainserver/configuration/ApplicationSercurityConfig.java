package vn.whoever.mainserver.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class ApplicationSercurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("whoeverUserDetailsService")
	UserDetailsService userDetailsService;
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		//System.out.println("ADMIN: " + userDetailsService.loadUserByUsername("admin"));
		auth.userDetailsService(userDetailsService);
	}
	
	@Bean(name = "whoeverAuthenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http.authorizeRequests()
	  	.antMatchers("/", "/home").anonymous()
	  	.antMatchers("/login", "/mobile/users/login").permitAll()
	  	.antMatchers("/mobile/users/register").permitAll()
	  	.antMatchers("/mobile/users/anonymous").permitAll()
	  	.antMatchers("/mobile/users/query").permitAll()
	  	
	  	.antMatchers("/mobile/news").permitAll() //access("hasRole('ROLE_USER')")
	  	.antMatchers("/mobile/status").permitAll() //access("hasRole('ROLE_USER')")
	  	.antMatchers("/mobile/status/*").permitAll() //access("hasRole('ROLE_USER')")
	  	
	  	.antMatchers("/mobile/friends/").permitAll() //access("hasRole('ROLE_USER')")
	  	.antMatchers("/mobile/friends/search").permitAll()
	  	.antMatchers("/mobile/friends/add").permitAll()
	  	
	  	.antMatchers("/mobile/profiles").permitAll() //access("hasRole('ROLE_USER')")
	  	.antMatchers("/mobile/profiles/*").permitAll() //access("hasRole('ROLE_USER')")
	  	
	  	.antMatchers("/mobile/comments").permitAll() //access("hasRole('ROLE_USER')")
	  	
//	  	.antMatchers("/admin/**").access("hasRole('ADMIN')")
//	  	.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
//	  	.and().formLogin().loginPage("/login")
//	  	.usernameParameter("ssoId").passwordParameter("password")
	  	.and().exceptionHandling().accessDeniedPage("/Access_Denied");
	  
	  	http.csrf().disable();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	     web.ignoring()
	        .antMatchers("/");
	}

}
