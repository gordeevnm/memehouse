package ru.kek.memehouse.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kek.memehouse.configuration.security.AuthenticationToken;
import ru.kek.memehouse.configuration.security.exceptions.AuthException;
import ru.kek.memehouse.models.AuthInfo;
import ru.kek.memehouse.models.TokenModel;
import ru.kek.memehouse.models.User;
import ru.kek.memehouse.services.AuthService;

/**
 * gordeevnm@gmail.com
 * 07.10.17
 */
@Service
public class AuthServiceImpl implements AuthService {
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public AuthServiceImpl(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public AuthInfo login(String username, String password) {
		User user = User.findByUsername(username);

		if (user == null || !passwordEncoder.matches(password, user.password))
			throw new AuthException("Username or password incorrect");

		TokenModel authToken = TokenModel.create(user);

		return AuthInfo.builder()
				.authToken(authToken.toString())
				.userInfo(user)
				.build();
	}

	@Override
	public void logout(AuthenticationToken token) {
		token.getCredentials().delete();
	}

	@Override
	public AuthInfo registration(AuthInfo authInfo) {

		return null;
	}
}
