package ru.kek.memehouse.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * gordeevnm@gmail.com
 * 14.01.18
 */
@Component
@RequiredArgsConstructor
public class ObjectMapperBean {
	private final ApplicationContext applicationContext;
	
	@PostConstruct
	private void initInstance() {
		instance = this;
	}
	
	private static ObjectMapperBean instance;
	
	public static ObjectMapper get() {
		return instance.applicationContext.getBean(ObjectMapper.class);
	}
}
