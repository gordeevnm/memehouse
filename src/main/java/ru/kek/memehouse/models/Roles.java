package ru.kek.memehouse.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * gordeevnm@gmail.com
 * 06.10.17
 */
public class Roles {
	public static final GrantedAuthority ROLE_USER = new SimpleGrantedAuthority("ROLE_USER");
	public static final GrantedAuthority ROLE_ADMIN = new SimpleGrantedAuthority("ROLE_ADMIN");
	public static final GrantedAuthority ROLE_ANONYMOUS = new SimpleGrantedAuthority("ROLE_ANONYMOUS");
}
