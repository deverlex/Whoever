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

import vn.whoever.mainserver.service.AuthenticalToken;
import vn.whoever.support.utils.FormatDate;

public class ApplicationFilterConfig implements Filter {

	@Autowired
	private AuthenticalToken authenticalToken;
	
	private FormatDate formatDate = new FormatDate();

	public void destroy() {

	}
	
	public void init(FilterConfig config) throws ServletException {

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest request = getAsHttpServletRequest(req);
		HttpServletResponse response = (HttpServletResponse) res;
		System.out.println(request.getRequestURI());
		
//		if (!request.getRequestURI().equals("/mainserver/") && !request.getRequestURI().equals("/mainserver/home")) {
//			if (request.getRequestURI().indexOf("/login") > 1) {
//				filterChain.doFilter(req, res);
//			}
//			filterChain.doFilter(req, res);
//			else {
//				/**
//				 * TODO: check token of user
//				 * 
//				 */
//				String token = extractAuthTokenFromRequest(request);
//				Date timeExp = exactAuthTokenTimeExpiredFromRequest(request);
//				if(timeExp.getTime() - (new Date()).getTime() > 0) {
//					// TODO: new time expiration for token
//					if(authenticalToken.validate(token)) {
//						filterChain.doFilter(req, res);
//					}
//				} else {
//					if(authenticalToken.validate(token)) {
//						long newTimeExp = (new Date()).getTime() + 24*60*60*1000;
//						String newToken = authenticalToken.getUpdateToken(token, new Date(newTimeExp));
//						response.setHeader("Whoever-Token", newToken);
//						response.setHeader("Token-expiration", (new Date(newTimeExp)).toString());
//						filterChain.doFilter(req, response);
//					}
//				}
//			}
//		} else {
		try {
			filterChain.doFilter(req, res);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			System.out.println("filter exception!!!!");
		}
			
//		}
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
		try {
			if(strDate == null) {
				strDate = request.getParameter("expired");
				return formatDate.toDateUri(strDate);
			}
			return formatDate.toDate(strDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date();
	}
	

}
