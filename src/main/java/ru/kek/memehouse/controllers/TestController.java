package ru.kek.memehouse.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.kek.memehouse.models.User;

/**
 * gordeevnm@gmail.com
 * 05.10.17
 */
@RestController
public class TestController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public User test0(Authentication auth) {
		System.out.println("index");
		if (auth == null)
			return User.builder().build();
		else
			return (User) auth.getDetails();
	}
	
	@RequestMapping(value = "/api/test", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public User test1(Authentication auth) {
		System.out.println("/api/test");
		return (User) auth.getDetails();
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public User test2(Authentication auth) {
		if (auth == null)
			return User.builder().build();
		else
			return (User) auth.getDetails();
	}
}
