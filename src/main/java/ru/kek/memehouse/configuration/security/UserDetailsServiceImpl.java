package ru.kek.memehouse.configuration.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kek.memehouse.models.User;

/**
 * gordeevnm@gmail.com
 * 06.10.17
 */
public class UserDetailsServiceImpl implements UserDetailsService {
	@Override
	public UserDetailsImpl loadUserByUsername(String token) throws UsernameNotFoundException {
		User user = User.findByToken(token);
		if (user == null)
			throw new UsernameNotFoundException("Auth fail(user with this token not found)");

		return new UserDetailsImpl(user);
	}
}
