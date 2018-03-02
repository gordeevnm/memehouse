package ru.kek.memehouse.dao.repos;

import org.springframework.stereotype.Repository;
import ru.kek.memehouse.models.Meme;

import java.util.List;
import java.util.Optional;

/**
 * gordeevnm@gmail.com
 * 14.01.18
 */
@Repository
public interface MemesRepo extends org.springframework.data.repository.Repository<Meme, Long> {
	Optional<Meme> findById(Long id);
	
	List<Meme> findAll(Iterable<Long> ids);
	
	Meme save(Meme entity);
	
	List<Meme> save(Iterable<Meme> entities);
	
	boolean exists(Long id);
	
	long count();
	
	void delete(Long id);
	
	void delete(Meme meme);
	
	void delete(Iterable<Meme> entities);
	
	void deleteAll();
}
