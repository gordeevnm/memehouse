package ru.kek.memehouse.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.kek.memehouse.dao.interfaces.MemesDao;
import ru.kek.memehouse.models.Meme;

import java.util.Map;
import java.util.Optional;

/**
 * gordeevnm@gmail.com
 * 14.01.18
 */
@Repository
public class MemesDaoJdbcImpl implements MemesDao {
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	private static final String CREATE_MEME_SQL =
		  "select *" +
				 "from insert_meme(created_by := :created_by :: bigint," +
				 "                 description := :description :: varchar," +
				 "                 name := :name :: varchar," +
				 "                 create_time := :create_time :: timestamp," +
				 "                 is_public := :is_public :: boolean," +
				 "                 tags_array := :tags_array :: varchar [])";
	
	private Map<String, Object> getParams() {
		return null;
	}
	
	@Override
	public void create(Meme meme) {
		namedJdbcTemplate.update(CREATE_MEME_SQL, getParams());
	}
	
	@Override
	public Optional<Meme> findById(int memeId) {
		throw new UnsupportedOperationException("Implementation not supported");
	}
}
