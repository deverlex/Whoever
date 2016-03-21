package vn.whoever.server.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

/**
 * @author spider man
 *	Class nay chua su dung
 */

@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

	@Bean
	public JedisConnectionFactory connectionFactory() {
		
		JedisConnectionFactory factory = new JedisConnectionFactory();
		System.out.println("time outttttt: " + factory.getTimeout());
		return new JedisConnectionFactory();
	}
	
	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		HeaderHttpSessionStrategy sessionStrategy = new HeaderHttpSessionStrategy();
		return new HeaderHttpSessionStrategy();
	}
}
