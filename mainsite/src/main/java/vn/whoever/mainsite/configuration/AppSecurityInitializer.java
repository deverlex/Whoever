package vn.whoever.mainsite.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class AppSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
	
	public AppSecurityInitializer() {
		super(AppSecurityConfig.class);
	}
	
}
