package ru.kek.memehouse.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * gordeevnm@gmail.com
 * 09.11.17
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionRepositoryConfig {
	@Value("${security.session-repository.password}")
	private String password;
	@Value("${security.session-repository.host}")
	private String host;
	@Value("${security.session-repository.port}")
	private int port;
	
	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(host, port);
		connectionFactory.setPassword(password);
		
		return connectionFactory;
	}
	
//	@Bean
//	public WebSessionIdResolver httpSessionIdResolver() {
//		HeaderWebSessionIdResolver resolver = new HeaderWebSessionIdResolver();
//		resolver.setHeaderName(WebSecurityConfig.SESSION_HEADER_NAME);
//
//		return resolver;
//	}
}