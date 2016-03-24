package vn.whoever.mainsite.configuration;

import javax.servlet.Filter;
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
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer 
	implements WebApplicationInitializer  {
	
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(AppConfig.class);
		ctx.setServletContext(servletContext);
		
		ServletRegistration.Dynamic servlet_web 
			= servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet_web.addMapping("/");
		servlet_web.setLoadOnStartup(1);
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new AppFilter()};
	}
}
