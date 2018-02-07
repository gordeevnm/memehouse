package ru.kek.memehouse.dao.impl;

import org.intellij.lang.annotations.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import ru.kek.memehouse.dao.interfaces.UsersDao;
import ru.kek.memehouse.models.User;

import javax.sql.DataSource;
import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * gordeevnm@gmail.com
 * 14.01.18
 */
@Repository
public class UsersDaoJdbcImpl implements UsersDao {
	@Language("PostgreSQL")
	private static final String FIND_WITH_FUTURE_BANS_BY_USERNAME =
			"SELECT * " +
					"FROM user_with_bans_view uv " +
					"WHERE uv.u_username = :username " +
					"   AND uv.u_is_deleted = FALSE " +
					"   AND (uv.b_end_time > :currentTimestamp " +
					"       OR uv.b_end_time IS NULL) " +
					"   AND (uv.b_is_active = TRUE " +
					"       OR uv.b_is_active IS NULL)";
	@Language("PostgreSQL")
	private static final String CREATE_USER_SQL =
			"INSERT INTO public.user (username, password, email, registration_time, roles) " +
					"VALUES (:username, :password, :email, :registrationTime, :roles) " +
					"RETURNING id";
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@Override
	public Optional<User> findByUsername(String username) {
		return Optional.empty();
	}
	
	@Override
	public Optional<User> findWithFutureBansByUsername(String username) {
		Map<String, Object> params = new HashMap<>();
		params.put("username", username);
		params.put("currentTimestamp", new Timestamp(System.currentTimeMillis()));
		
		try {
			return Optional.ofNullable(
					namedJdbcTemplate.query(
							FIND_WITH_FUTURE_BANS_BY_USERNAME,
							params,
							User.WITH_BANS_VIEW_RS_EXTRACTOR
					)
			);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public void create(User user) {
		Map<String, Object> params = new HashMap<>();
		params.put("username", user.getUsername());
		params.put("password", user.getPassword());
		params.put("email", user.getEmail());
		params.put("registrationTime", user.getRegistrationTime());
		String[] rolesStringArr = user.getRoles().stream()
				.map(GrantedAuthority::getAuthority)
				.toArray(String[]::new);
		Array array = null;
		try {
			Connection connection = dataSource.getConnection();
			array = connection.createArrayOf("VARCHAR", rolesStringArr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		params.put("roles", array);
		
		int createdUserId = namedJdbcTemplate.queryForObject(
				CREATE_USER_SQL,
				params,
				int.class
		);
		user.setId(createdUserId);
	}
}
