package vn.whoever.mainserver.configuration;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import vn.whoever.mainserver.service.AuthToken;
import vn.whoever.mainserver.service.UsersService;

/**
 * @author Nguyen Van Do 
 * This file config filter each requests from client to
 * server My idea is filter tokenId of users in this file
 */

public class ApplicationFilterConfig implements Filter {

	// this annotation for check tokenId
	@Autowired
	private AuthToken authToken;

	// this annotation for authentication for users.
	@Autowired
	private UsersService usersService;

	public void init(FilterConfig config) throws ServletException {	}

	// Implement funtion doFilter() from Filter interface
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = getAsHttpServletRequest(req);
		HttpServletResponse response = (HttpServletResponse) res;

		try {
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			System.out.println("filter exception!!!!");
		}
	}

	private HttpServletRequest getAsHttpServletRequest(ServletRequest req) {
		if (req instanceof HttpServletRequest) {
			return (HttpServletRequest) req;
		}
		throw new RuntimeException("Expecting an HTTP Request");
	}

	public void destroy() {}
}
