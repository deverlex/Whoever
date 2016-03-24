package vn.whoever.mainsite.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//		
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http.authorizeRequests()
	  	.antMatchers("/", "/home").permitAll()
	  	.and().formLogin().loginPage("/login")
	  	.and().exceptionHandling().accessDeniedPage("/access_denied");
	
	  http.csrf().disable();
	}
	
}
