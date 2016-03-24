package vn.whoever.mainsite.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * such as web.xml
 * @author spider man
 *
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx_web = new AnnotationConfigWebApplicationContext();
		ctx_web.register(AppWebConfig.class);
		ctx_web.setServletContext(servletContext);
		
		ServletRegistration.Dynamic servlet_web 
			= servletContext.addServlet("dispatcher-web", new DispatcherServlet(ctx_web));
		servlet_web.setLoadOnStartup(2);
		servlet_web.addMapping("/web/*");
		
		AnnotationConfigWebApplicationContext ctx_mobile = new AnnotationConfigWebApplicationContext();
		ctx_mobile.register(AppMobileConfig.class);
		ctx_mobile.setServletContext(servletContext);
		
		ServletRegistration.Dynamic servlet_mobile
			= servletContext.addServlet("dispatcher-mobile", new DispatcherServlet(ctx_mobile));
		servlet_mobile.setLoadOnStartup(1);
		servlet_mobile.addMapping("/mobile/*");
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {AppWebConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/*"};
	}

	
}
