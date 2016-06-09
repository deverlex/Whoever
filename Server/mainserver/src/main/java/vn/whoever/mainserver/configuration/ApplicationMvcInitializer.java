package vn.whoever.mainserver.configuration;

import javax.servlet.Filter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author Nguyen Van Do
 *
 * Configuration servelet mapping and filter service in this file
 */
public class ApplicationMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// return class config/define servlet for whoever web service
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { ApplicationConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] { new ApplicationFilterConfig() };
	}
}
