package ru.kek.memehouse.configuration.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kek.memehouse.models.User;

import java.util.Collection;

/**
 * gordeevnm@gmail.com
 * 06.10.17
 */
public class UserDetailsImpl extends User implements UserDetails {
	private final User user;

	UserDetailsImpl(User user) {
		super(user);
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.authorities;
	}

	@Override
	public String getPassword() {
		return user.password;
	}

	@Override
	public String getUsername() {
		return user.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
