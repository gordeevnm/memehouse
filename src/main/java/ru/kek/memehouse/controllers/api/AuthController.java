package ru.kek.memehouse.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kek.memehouse.configuration.security.AuthenticationToken;
import ru.kek.memehouse.models.AuthInfo;
import ru.kek.memehouse.services.AuthService;

/**
 * gordeevnm@gmail.com
 * 07.10.17
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private final AuthService authService;

	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public AuthInfo login(@RequestHeader("username") String username,
	                      @RequestHeader("password") String password) {
		return authService.login(username, password);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void logout(AuthenticationToken token) {
		authService.logout(token);
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public AuthInfo registration(AuthInfo authInfo) {
		return authService.registration(authInfo);
	}
}
