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

/**
 * @author Nguyen Van Do
 *	In this file, configuration are implemented for accessing to resource (URI) on web service
 *	by using require role, authenticated and set login form for web
 */

@Configuration
@EnableWebSecurity
public class ApplicationSercurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("whoeverUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean(name = "whoeverAuthenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").anonymous()

				// accessing to function login/access in Whoever social
				// networking
				.antMatchers("/mobile/users/login").permitAll().antMatchers("/mobile/users/register").permitAll()
				.antMatchers("/mobile/users/anonymous").permitAll().antMatchers("/mobile/users/query").permitAll()
				.antMatchers("/mobile/users/reconnect").permitAll()

				// accessing to functions interactive with status of users
				// Whoever
				.antMatchers("/mobile/news").authenticated().antMatchers("/mobile/status").authenticated()
				.antMatchers("/mobile/status/*").authenticated()

				// accessing to function: Search, add and delete
				// contacts/friends for users
				.antMatchers("/mobile/friends/").authenticated().antMatchers("/mobile/friends/search").authenticated()
				.antMatchers("/mobile/friends/add").authenticated()

				// accessing to function: get profile
				.antMatchers("/mobile/profiles").authenticated().antMatchers("/mobile/profiles/*").authenticated()
				.antMatchers("/mobile/comments").authenticated() // access("hasRole('ROLE_USER')")
				// .antMatchers("/admin").authenticated()

				.antMatchers("/admin").access("hasRole('ROLE_ADMIN')").antMatchers("/start")
				.access("hasRole('ROLE_ADMIN')").antMatchers("/stop").access("hasRole('ROLE_ADMIN')")

				// enable login form to web
				.and().formLogin().loginPage("/home").usernameParameter("ssoId").passwordParameter("password")
				.defaultSuccessUrl("/admin").and().exceptionHandling().accessDeniedPage("/Access_Denied");

		http.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/");
	}

}
