package ru.kek.memehouse.dao.interfaces;

import ru.kek.memehouse.models.User;

import java.util.Optional;

/**
 * gordeevnm@gmail.com
 * 09.01.18
 */
public interface UsersDao {
	Optional<User> findByUsername(String username);
	
	Optional<User> findWithFutureBansByUsername(String username);
	
	void create(User user);
}
