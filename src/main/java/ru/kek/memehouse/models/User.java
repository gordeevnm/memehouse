package ru.kek.memehouse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import ru.kek.memehouse.models.additional.JacksonDto;

import java.util.List;

/**
 * gordeevnm@gmail.com
 * 05.10.17
 */
@JacksonDto
@Builder
@AllArgsConstructor
public class User {
	public final List<GrantedAuthority> authorities;
	public final String username;
	public final String password;
	private List<Meme> savedMemes;
	private boolean isBanned;
	private boolean isDeleted;

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
