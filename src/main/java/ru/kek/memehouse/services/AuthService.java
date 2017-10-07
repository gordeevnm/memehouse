package ru.kek.memehouse.services;

import ru.kek.memehouse.configuration.security.AuthenticationToken;
import ru.kek.memehouse.models.AuthInfo;

/**
 * gordeevnm@gmail.com
 * 07.10.17
 */
public interface AuthService {
	AuthInfo login(String username, String password);

	void logout(AuthenticationToken token);

	AuthInfo registration(AuthInfo authInfo);
}
