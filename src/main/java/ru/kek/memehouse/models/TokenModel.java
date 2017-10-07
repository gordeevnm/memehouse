package ru.kek.memehouse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 * gordeevnm@gmail.com
 * 07.10.17
 */
@Builder
@AllArgsConstructor
public class TokenModel extends Model {
	private static final int TOKEN_LENGTH = 60;

	private long id;
	private String token;

	public TokenModel(String token) {
		this.token = token;
	}

	private static final String CREATE_TOKEN_SQL =
			"INSERT INTO token (user_id, token) VALUES (?, ?)";

	public static TokenModel create(User user) {
		String token = generate();
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		return jdbcTemplate.queryForObject(CREATE_TOKEN_SQL, TokenModel::rowMapper, user.id, token);
	}

	private static TokenModel rowMapper(ResultSet rs, int rowNum) throws SQLException {
		return TokenModel.builder()
				.id(rs.getLong("id"))
				.token(rs.getString("token"))
				.build();
	}

	private static final char[] CHARS = {
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
	};
	public static final Random random = new Random();

	private static String generate() {
		char[] buffer = new char[TOKEN_LENGTH];
		for (int i = 0; i < buffer.length; i++)
			buffer[i] = CHARS[random.nextInt(CHARS.length)];

		return new String(buffer);
	}

	@Override
	public String toString() {
		return token;
	}
}
