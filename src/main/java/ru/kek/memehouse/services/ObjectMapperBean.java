package ru.kek.memehouse.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * gordeevnm@gmail.com
 * 14.01.18
 */
@Component
public class ObjectMapperBean {
	@Autowired
	private ApplicationContext applicationContext;
	
	@PostConstruct
	private void initInstance() {
		instance = this;
	}
	
	private static ObjectMapperBean instance;
	
	public static ObjectMapper get() {
		return instance.applicationContext.getBean(ObjectMapper.class);
	}
}
