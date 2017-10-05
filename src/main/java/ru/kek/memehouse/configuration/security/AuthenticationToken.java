package ru.kek.memehouse.configuration.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * gordeevnm@gmail.com
 * 06.10.17
 */
public class AuthenticationToken implements Authentication {
	private String token;
	private UserDetails userDetails;
	private boolean isAuthenticated;

	void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	AuthenticationToken(String token) {
		this.token = token;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return userDetails.getAuthorities();
	}

	@Override
	public String getCredentials() {
		return token;
	}

	@Override
	public UserDetails getDetails() {
		return userDetails;
	}

	@Override
	public UserDetails getPrincipal() {
		return userDetails;
	}

	@Override
	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		this.isAuthenticated = isAuthenticated;
	}

	@Override
	public String getName() {
		return userDetails.getUsername();
	}
}
