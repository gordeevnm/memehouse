package ru.kek.memehouse.dao.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kek.memehouse.models.User;

import java.util.List;
import java.util.Optional;

/**
 * gordeevnm@gmail.com
 * 09.01.18
 */
@Repository
public interface UsersRepo extends org.springframework.data.repository.Repository<User, Long> {
	@Query("select User from User where username=:username")
	Optional<User> findByUsername(@Param("username") String username);
	
	Optional<User> findById(Long id);
	
	User save(User entity);
	
	List<User> save(Iterable<User> entities);
	
	boolean exists(Long id);
	
	List<User> findAll(Iterable<Long> ids);
	
	long count();
}