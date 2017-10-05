package ru.kek.memehouse.models;

import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

/**
 * gordeevnm@gmail.com
 * 06.10.17
 */
@EqualsAndHashCode
public class CustomGrantedAuthority implements GrantedAuthority {
	public static final GrantedAuthority ROLE_USER = new CustomGrantedAuthority("ROLE_USER");
	public static final GrantedAuthority ROLE_ADMIN = new CustomGrantedAuthority("ROLE_ADMIN");
	public static final GrantedAuthority ROLE_ANONYMOUS = new CustomGrantedAuthority("ROLE_ANONYMOUS");

	private final String role;

	public CustomGrantedAuthority(String role) {
		this.role = role;
	}

	@Override
	public String getAuthority() {
		return role;
	}

	@Override
	public String toString() {
		return role;
	}
}
