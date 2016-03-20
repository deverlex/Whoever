package vn.whoever.server.configuration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import antlr.collections.Enumerator;
import vn.whoever.server.entity.User;
import vn.whoever.server.utils.TokenUtils;
import vn.whoever.server.utils.TokenUtilsImpl;

public class AuthenticationProcessingFilter extends GenericFilterBean {
	
	TokenUtils tokenUtils = new TokenUtilsImpl();
	AuthenticationManager authManager;
	
	public AuthenticationProcessingFilter(AuthenticationManager authManager) {
		this.authManager = authManager;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		System.out.println("Request!!!");
		Map<String, String[]> params = request.getParameterMap();
		
		if(params.containsKey("token")) {
			String token = params.get("token")[0];
			
			//validate token
			if(tokenUtils.validate(token)) {
				//determine the user base on the (already validated) token
				User user = tokenUtils.getUserFromToken(token);
				
				// build an Authentication object with the user's info
				UsernamePasswordAuthenticationToken authToken = 
						new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
				// set the authentication into the SecurityContext
				SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(authToken));
				
				//setting for response
				HttpServletResponse httpResponse = (HttpServletResponse) response;
				
			}
		} else {
			// send to client a token
		}
		//((HttpServletResponse)response).setHeader("Access-Control-Allow-Headers", "x-auth-token, x-requested-with");
		chain.doFilter(request, response);
	}

	
	private HttpServletRequest getAsHttpRequest(ServletRequest request) {
		if(!(request instanceof HttpServletRequest)) {
			throw new RuntimeException("Expecting an HTTP request");
		}
		
		return (HttpServletRequest) request;
	}
	
	private String extractAuthTokenFromRequest(HttpServletRequest httpRequest) {
		/* Get token from header */
		String authToken = httpRequest.getHeader("X-Auth-Token");
		
		/* If token is not found then it is gotten from params */
		if(authToken == null) {
			System.out.println("Not found Token from Header!!");
			authToken = httpRequest.getParameter("token");
		}
		
		return authToken;
	}

}
