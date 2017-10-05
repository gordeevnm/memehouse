package ru.kek.memehouse.configuration.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kek.memehouse.configuration.security.exceptions.AuthException;

/**
 * gordeevnm@gmail.com
 * 06.10.17
 */
public class TokenAuthenticationManager implements AuthenticationManager {
	private UserDetailsService userDetailsService;

	TokenAuthenticationManager(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		AuthenticationToken tokenAuthentication = (AuthenticationToken) authentication;
		UserDetails userDetails;
		try {
			userDetails = userDetailsService.loadUserByUsername(tokenAuthentication.getCredentials());
		} catch (UsernameNotFoundException e) {
			throw new AuthException(e.getMessage());
		}
		tokenAuthentication.setUserDetails(userDetails);
		tokenAuthentication.setAuthenticated(true);

		return tokenAuthentication;
	}
}
