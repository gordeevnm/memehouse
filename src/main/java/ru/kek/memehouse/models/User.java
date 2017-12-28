package ru.kek.memehouse.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.GrantedAuthority;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * gordeevnm@gmail.com
 * 05.10.17
 */
@Builder
@AllArgsConstructor
public class User extends MongoDoc {
	public long id;
	public List<GrantedAuthority> authorities;
	public String username;
	@JsonIgnore
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

	private static final String SELECT_USER_BY_USERNAME_SQL =
			"SELECT * FROM client u WHERE u.username = ?";

	private static final String SELECT_USER_BY_TOKEN_SQL =
			"SELECT u.* FROM client u JOIN token t ON t.client_id = u.id WHERE t.token = ?";

	public static User findByToken(String token) {
		User user;
		try {
			user = getJdbcTemplate().queryForObject(SELECT_USER_BY_TOKEN_SQL, User::rowMapper, token);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return user;
	}

	public static User findByUsername(String username) {
		User user;
		try {
			user = getJdbcTemplate().queryForObject(SELECT_USER_BY_USERNAME_SQL, User::rowMapper, username);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return user;
	}

	private static User rowMapper(ResultSet rs, int i) throws SQLException {
		return User.builder()
				.id(rs.getLong("id"))
				.username(rs.getString("username"))
				.password(rs.getString("password"))
				.build();
	}
	
	public static class UserDto {
	}
}
