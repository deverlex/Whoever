package vn.whoever.mainserver.configuration;

import javax.servlet.Filter;

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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
	  	.antMatchers("/").anonymous()
	  	.antMatchers("/mobile/users/login").permitAll()
	  	.antMatchers("/mobile/users/register").permitAll()
	  	.antMatchers("/mobile/users/anonymous").permitAll()
	  	.antMatchers("/mobile/users/query").permitAll()
	  	.antMatchers("/mobile/users/reconnect").permitAll()
	  	
	  	.antMatchers("/mobile/news").authenticated()
	  	.antMatchers("/mobile/status").authenticated()
	  	.antMatchers("/mobile/status/*").authenticated()
	  	
	  	.antMatchers("/mobile/friends/").authenticated()
	  	.antMatchers("/mobile/friends/search").authenticated()
	  	.antMatchers("/mobile/friends/add").authenticated()
	  	
	  	.antMatchers("/mobile/profiles").authenticated()
	  	.antMatchers("/mobile/profiles/*").authenticated()
	  	
	  	.antMatchers("/mobile/comments").authenticated() //access("hasRole('ROLE_USER')")
	  	//.antMatchers("/admin").authenticated()
	  	
	  	.antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
	  	.antMatchers("/start").access("hasRole('ROLE_ADMIN')")
	  	.antMatchers("/stop").access("hasRole('ROLE_ADMIN')")
//	  	.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
	  	.and().formLogin().loginPage("/home")
	  	.usernameParameter("ssoId").passwordParameter("password").defaultSuccessUrl("/admin")
	  	.and().exceptionHandling().accessDeniedPage("/Access_Denied");
	  
	  	http.csrf().disable();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	     web.ignoring()
	        .antMatchers("/");
	}

}
