package ru.kek.memehouse.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.kek.memehouse.models.User;

import javax.servlet.http.HttpSession;

/**
 * gordeevnm@gmail.com
 * 05.10.17
 */
@RestController
public class TestController {
	@RequestMapping(value = "/api/test", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public User test0(Authentication auth) {
		System.out.println("index");
		if (auth == null)
			return User.builder().build();
		else
			return (User) auth.getDetails();
	}
	
	@RequestMapping(value = "/api/session", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public HttpSession test1(HttpSession session) {
		System.out.println(session.getId());
		return session;
	}
	
	@RequestMapping(value = "/api/authentication", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Authentication test2(Authentication auth) {
		return auth;
	}
}
