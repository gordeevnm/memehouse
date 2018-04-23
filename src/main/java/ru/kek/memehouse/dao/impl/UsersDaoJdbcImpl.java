package ru.kek.memehouse.dao.impl;

import org.intellij.lang.annotations.Language;
import org.springframework.stereotype.Repository;
import ru.kek.memehouse.dao.interfaces.UsersDao;
import ru.kek.memehouse.models.User;

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
	
	@Override
	public Optional<User> findByUsername(String username) {
		throw new UnsupportedOperationException("Implementation not supported");
	}
	
	@Override
	public Optional<User> findWithFutureBansByUsername(String username) {
		throw new UnsupportedOperationException("Implementation not supported");
	}
	
	@Override
	public void create(User user) {
		throw new UnsupportedOperationException("Implementation not supported");
	}
}
