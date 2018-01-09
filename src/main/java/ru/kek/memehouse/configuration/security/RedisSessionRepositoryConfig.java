package ru.kek.memehouse.configuration.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * gordeevnm@gmail.com
 * 09.11.17
 */
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
//
//	@Bean
//	public RedisTemplate<Object, Object> sessionRedisTemplate(RedisConnectionFactory connectionFactory) {
//		ObjectMapper mapper = customObjectMapper();
//		RedisTemplate<Object, Object> template = new RedisTemplate<>();
//		template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer(mapper));
//		template.setKeySerializer(template.getStringSerializer());
//		template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer(mapper));
//		template.setValueSerializer(new GenericJackson2JsonRedisSerializer(mapper));
//		template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer(mapper));
//		template.setConnectionFactory(connectionFactory);
//		return template;
//	}
//
//	private ObjectMapper customObjectMapper() {
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.setVisibility(
//				mapper.getVisibilityChecker()
//						.withFieldVisibility(JsonAutoDetect.Visibility.ANY)
//						.withGetterVisibility(JsonAutoDetect.Visibility.NONE)
//						.withSetterVisibility(JsonAutoDetect.Visibility.NONE)
//						.withCreatorVisibility(JsonAutoDetect.Visibility.NONE));
//		mapper.registerModules(SecurityJackson2Modules.getModules(getClass().getClassLoader()));
////		mapper.enableDefaultTypingAsProperty(ObjectMapper.DefaultTyping.NON_FINAL, "@class"); // enable default typing
//		return mapper;
//	}
}