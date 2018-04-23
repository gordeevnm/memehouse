package ru.kek.memehouse.dao.impl;

import org.springframework.stereotype.Repository;
import ru.kek.memehouse.dao.interfaces.MemesDao;
import ru.kek.memehouse.models.Meme;

import java.util.Optional;

/**
 * gordeevnm@gmail.com
 * 14.01.18
 */
@Repository
public class MemesDaoJdbcImpl implements MemesDao {
	
	@Override
	public void create(Meme meme) {
		throw new UnsupportedOperationException("Implementation not supported");
	}
	
	@Override
	public Optional<Meme> findById(int memeId) {
		throw new UnsupportedOperationException("Implementation not supported");
	}
}
