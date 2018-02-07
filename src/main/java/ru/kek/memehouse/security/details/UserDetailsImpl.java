package ru.kek.memehouse.security.details;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kek.memehouse.models.User;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails, CredentialsContainer {
	private User user;
	
	public UserDetailsImpl(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return user.getRoles();
	}
	
	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		// TODO: 14.01.18 impl
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return !user.isDeleted();
	}
	
	public int getId() {
		return user.getId();
	}
	
	@Override
	public void eraseCredentials() {
		this.user.setPassword(null);
	}
	
	public User getRawUser() {
		return user;
	}
}