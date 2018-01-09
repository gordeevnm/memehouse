package ru.kek.memehouse.dao;

import ru.kek.memehouse.models.User;

import java.util.Optional;

/**
 * gordeevnm@gmail.com
 * 09.01.18
 */
public interface UsersDao {
	Optional<User> findByUsername(String username);
	
	Optional<User> findWithCurrentBanByUsername(String username);
}
