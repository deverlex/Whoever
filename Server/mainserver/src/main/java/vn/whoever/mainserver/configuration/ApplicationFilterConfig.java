package vn.whoever.mainserver.configuration;

import java.io.IOException;
import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApplicationFilterConfig implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = getAsHttpServletRequest(req);
		HttpServletResponse response = (HttpServletResponse) res;

		Map<String, String[]> params = req.getParameterMap();
		
		System.out.println("URL request " + request.getRequestURL());
		System.out.println("URI request " + request.getRequestURI());
		System.out.println("Token: " + extractAuthTokenFromRequest(request));
		
		if(request.getRequestURI().indexOf("/login") > 1) {
			
		} else {
			
		}
		
		try {
			filterChain.doFilter(req, res);
		} catch(Exception e) {
			
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
		String authToken = request.getHeader("Postman-Token");
		
		if(authToken ==  null) {
			authToken = request.getParameter("whoever-token");
		}
		return authToken;
	}
	

}
