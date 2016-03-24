package vn.whoever.mainsite.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * such as: ....-servlet.xml
 * @author spider man
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "vn.whoever.mainsite.controller")
public class AppConfig extends WebMvcConfigurerAdapter {

//	@Override
//	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//		configurer.enable("dispatcher-web");
//	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	/**
	 * Config ResourceHandlers to server
	 */
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/scripts/**").addResourceLocations("/scripts/");
		registry.addResourceHandler("/sources/**").addResourceLocations("/WEB-INF/sources/");
	}
	
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(mappingJackson2HttpMessageConverter());
	}
	
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
		return converter;
	}
}
