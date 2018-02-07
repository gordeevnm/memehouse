package ru.kek.memehouse.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.intellij.lang.annotations.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.kek.memehouse.dao.interfaces.MemesDao;
import ru.kek.memehouse.models.Meme;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * gordeevnm@gmail.com
 * 14.01.18
 */
@Repository
public class MemesDaoJdbcImpl implements MemesDao {
	@Language("PostgreSQL")
	private static final String CREATE_MEME_SQL =
			"INSERT INTO meme(uploaded_by, description, name, upload_time, is_public, lurkmore_link, attachment, tags_array) " +
					"VALUES (:uploadedBy, :description, :name, :uploadTime, :isPublic, :lurkmoreLink, :attachment::JSON, :tagsArray) " +
					"RETURNING meme.id";
	@Language("PostgreSQL")
	private static final String FIND_MEME_BY_ID_SQL =
			"SELECT * " +
					"FROM meme " +
					"WHERE id = :memeId " +
					"   AND is_deleted = FALSE";
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public void create(Meme meme) {
		Map<String, Object> params = getParams(meme);
		int createdMemeId = namedJdbcTemplate.queryForObject(CREATE_MEME_SQL, params, int.class);
		meme.setId(createdMemeId);
	}
	
	@Override
	public Optional<Meme> findById(int memeId) {
		Map<String, Integer> params = Collections.singletonMap("memeId", memeId);
		try {
			return Optional.of(
					namedJdbcTemplate.queryForObject(
							FIND_MEME_BY_ID_SQL,
							params,
							Meme.DEF_ROW_MAPPER
					)
			);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
	
	@SneakyThrows
	private Map<String, Object> getParams(Meme meme) {
		Map<String, Object> params = new HashMap<>();
		params.put("uploadedBy", meme.getUploadedBy());
		params.put("description", meme.getDescription());
		params.put("name", meme.getName());
		params.put("uploadTime", meme.getUploadTime());
		params.put("isPublic", meme.isPublic());
		params.put("lurkmoreLink", meme.getLurkmoreLink());
		params.put("attachment", objectMapper.writeValueAsString(meme.getAttachment()));
		params.put("tags", meme.getTags());
		params.put("isDeleted", meme.isDeleted());
		
		return params;
	}
}
