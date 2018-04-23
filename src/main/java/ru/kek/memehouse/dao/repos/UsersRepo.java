package ru.kek.memehouse.dao.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.kek.memehouse.models.User;

import java.util.Optional;

/**
 * gordeevnm@gmail.com
 * 09.01.18
 */
public interface UsersRepo extends CrudRepository<User, Long> {
	@Query("from User where username=:username")
	Optional<User> findByUsername(@Param("username") String username);
}