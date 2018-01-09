package ru.kek.memehouse.configuration.security.details;

import ru.kek.memehouse.models.User;

public class UserDetailsImpl extends org.springframework.security.core.userdetails.User {
	private boolean isDeleted;
	private long banEndTime;
	private int id;
	
	public UserDetailsImpl(User user) {
		super(user.getUsername(), user.getPassword(), user.getRoles());
		
		this.banEndTime = user.getCurrentBan().getEndTime().getTime();
		this.isDeleted = user.isDeleted();
		this.id = user.getId();
	}
	
	public boolean isDeleted() {
		return isDeleted;
	}
	
	public long getBanEndTime() {
		return banEndTime;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return banEndTime < System.currentTimeMillis();
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return !isDeleted;
	}
	
	public int getId() {
		return id;
	}
}