package ru.kek.memehouse.dao.interfaces;

import ru.kek.memehouse.models.Meme;

import java.util.Optional;

/**
 * gordeevnm@gmail.com
 * 14.01.18
 */
public interface MemesDao {
	void create(Meme meme);
	
	Optional<Meme> findById(int memeId);
}
