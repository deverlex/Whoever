package vn.whoever.mainserver.configuration;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import vn.whoever.mainserver.service.AuthenticalToken;

public class ApplicationFilterConfig implements Filter {

	@Autowired
	private AuthenticalToken authenticalToken;

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = getAsHttpServletRequest(req);
		HttpServletResponse response = (HttpServletResponse) res;
		
		if (!request.getRequestURI().equals("/mainserver/") || !request.getRequestURI().equals("/mainserver/home")) {
			if (request.getRequestURI().indexOf("/login") > 1) {
				/**
				 * TODO: generate token for user
				 * 
				 */
			} else {
				/**
				 * TODO: check token of user
				 * 
				 */
				String token = extractAuthTokenFromRequest(request);
				
			}
		} else {
			filterChain.doFilter(req, res);
		}
	}

	public void init(FilterConfig config) throws ServletException {

	}
	
	private HttpServletRequest getAsHttpServletRequest(ServletRequest req) {
		if(req instanceof HttpServletRequest) {
			return (HttpServletRequest) req;
		}
		throw new RuntimeException("Expecting an HTTP Request");
	}
	
	private String extractAuthTokenFromRequest(HttpServletRequest request) {
		/* get Token from Headers */
		String authToken = request.getHeader("Whoever-Token");
		
		if(authToken ==  null) {
			authToken = request.getParameter("token");
		}
		return authToken;
	}
	
	private Date exactAuthTokenTimeExpiredFromRequest(HttpServletRequest request) {
		String strDate = request.getHeader("Token-expiration");
		if(strDate == null) {
			strDate = request.getParameter("expired");
		}
	}
	

}
