package vn.whoever.mainserver.configuration;

import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
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

import vn.whoever.mainserver.service.AuthToken;
import vn.whoever.mainserver.service.UsersService;
import vn.whoever.support.utils.FormatDate;

public class ApplicationFilterConfig implements Filter {

	@Autowired
	private AuthToken authToken;
	
	@Autowired
	private UsersService usersService;
	
	private FormatDate formatDate = new FormatDate();

	public void destroy() {

	}
	
	public void init(FilterConfig config) throws ServletException {

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = getAsHttpServletRequest(req);
		HttpServletResponse response = (HttpServletResponse) res;
		
		System.out.println("URI: " + request.getRequestURI());
		
		try {
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			System.out.println("filter exception!!!!");
		}
	}
	
	
	private HttpServletRequest getAsHttpServletRequest(ServletRequest req) {
		if(req instanceof HttpServletRequest) {
			return (HttpServletRequest) req;
		}
		throw new RuntimeException("Expecting an HTTP Request");
	}
	
	

}
