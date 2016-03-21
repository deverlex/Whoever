package vn.whoever.server.configuration;

import javax.security.auth.login.AppConfigurationEntry;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * @author spider man
 *	class nay chua su dung
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	

	public WebSecurityConfig() {
		super(true);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		
		http.exceptionHandling().and()
		.anonymous().and()
		.servletApi().and()
		.headers().cacheControl().and()
		.authorizeRequests()
		
		//Allow anonymous resource request
		.antMatchers("/").permitAll()
		.antMatchers("/*.ico").permitAll()
		.antMatchers("**/*.html").permitAll()
		.antMatchers("**/*.css").permitAll()
		.antMatchers("**/*.js").permitAll()
		//Allow anonymous for request logins
		.antMatchers("/login/**").permitAll()
		// All other request need to be authenticated
		.anyRequest().authenticated().and()
		// Custom Token based authentication based on the header previously given to the client
		.addFilterAfter(null, null);
	
		http.logout().permitAll();
		System.out.println("Security Http Filter!!!");
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) {
		
	}
}
