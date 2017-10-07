package ru.kek.memehouse.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * gordeevnm@gmail.com
 * 05.10.17
 */
@Builder
@AllArgsConstructor
@JsonAutoDetect(
		fieldVisibility = JsonAutoDetect.Visibility.ANY,
		getterVisibility = JsonAutoDetect.Visibility.NONE,
		setterVisibility = JsonAutoDetect.Visibility.NONE)
public class User {
	public long id;
	public List<GrantedAuthority> authorities;
	public String username;
	public String password;
	private List<Meme> savedMemes;
	private boolean isBanned;
	private boolean isDeleted;

	public User(User user) {
		this.id = user.id;
		this.authorities = user.authorities;
		this.username = user.username;
		this.savedMemes = user.savedMemes;
		this.isBanned = user.isBanned;
		this.isDeleted = user.isDeleted;
	}

	public static User findByToken(String token) {
		// TODO: 06.10.17 реализовать
		return null;
	}

	public static User findByUsername(String username) {
		return null;
	}
}
