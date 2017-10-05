package ru.kek.memehouse.models;

import org.springframework.security.core.GrantedAuthority;
import ru.kek.memehouse.models.additional.JacksonDto;

import java.util.List;

/**
 * gordeevnm@gmail.com
 * 05.10.17
 */
@JacksonDto
public class User {
	public final List<GrantedAuthority> authorities;
	public final String username;
	public final String password;
	private List<Meme> savedMemes;

	public User(List<GrantedAuthority> authorities, String username, String password) {
		this.authorities = authorities;
		this.username = username;
		this.password = password;
	}

	public User(User user) {
		this.authorities = user.authorities;
		this.username = user.username;
		this.password = user.password;
	}

	public static User findByToken(String token) {
		// TODO: 06.10.17 реализовать
		return null;
	}
}
