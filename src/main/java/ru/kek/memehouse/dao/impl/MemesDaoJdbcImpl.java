package ru.kek.memehouse.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.kek.memehouse.dao.interfaces.MemesDao;
import ru.kek.memehouse.models.Meme;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * gordeevnm@gmail.com
 * 14.01.18
 */
@Repository
@RequiredArgsConstructor
public class MemesDaoJdbcImpl implements MemesDao {
	private final NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@SneakyThrows
	private java.sql.Array createSqlArray(String[] arr) {
		return namedJdbcTemplate
			  .getJdbcTemplate()
			  .getDataSource()
			  .getConnection()
			  .createArrayOf("varchar", arr);
	}
	
	@Override
	public void create(Meme meme) {
		final String sql =
			  "select * from insert_meme(created_by := :created_by::bigint," +
					 "                     description := :description::varchar," +
					 "                     name := :name::varchar," +
					 "                     picture_id := :picture_id::varchar," +
					 "                     create_time := :create_time::timestamp," +
					 "                     is_public := :is_public::boolean," +
					 "                     tags_array := :tags_array::varchar[])";
		
		Map<String, Object> params = new HashMap<>();
		params.put("created_by", meme.getCreatedById());
		params.put("description", meme.getDescription());
		params.put("picture_id", meme.getPictureId());
		params.put("name", meme.getName());
		params.put("create_time", meme.getCreateTime());
		params.put("is_public", meme.isPublic());
		params.put("tags_array", createSqlArray(meme.getTags()));
		
		final Meme savedMeme = namedJdbcTemplate.queryForObject(sql, params, Meme.DEF_ROW_MAPPER);
		meme.setId(savedMeme.getId());
		meme.setTags(savedMeme.getTags());
	}
	
	@Override
	public void update(Meme meme, Long updatedById) {
		final String sql =
			  "select * from update_meme(meme_id := :meme_id::bigint," +
				    "                     last_updated_by := :updated_by::bigint," +
				    "                     description := :description::varchar," +
				    "                     name := :name::varchar," +
				    "                     picture_id := :picture_id::varchar," +
					 "                     is_public := :is_public::boolean," +
					 "                     tags_array := :tags_array::varchar[])";
		
		Map<String, Object> params = new HashMap<>();
		params.put("meme_id", meme.getId());
		params.put("updated_by", updatedById);
		params.put("name", meme.getName());
		params.put("description", meme.getDescription());
		params.put("picture_id", meme.getPictureId());
		params.put("is_public", meme.isPublic());
		params.put("tags_array", createSqlArray(meme.getTags()));
		
		final Meme savedMeme = namedJdbcTemplate.queryForObject(sql, params, Meme.DEF_ROW_MAPPER);
		meme.setId(savedMeme.getId());
		meme.setTags(savedMeme.getTags());
	}
	
	@Override
	public Optional<Meme> findById(Long memeId) {
		final String sql = "select * from meme where id = :meme_id";
		try {
			Map<String, Object> params = Collections.singletonMap("meme_id", memeId);
			return Optional.of(namedJdbcTemplate.queryForObject(sql, params, Meme.DEF_ROW_MAPPER));
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}
	
	@Override
	public List<Meme> findAllById(List<Long> ids) {
		if (ids.isEmpty())
			return Collections.emptyList();
		
		final String sql = "select * from meme where id IN(:meme_ids)";
		try {
			Map<String, Object> params = Collections.singletonMap("meme_ids", ids);
			final Map<Long, Meme> memeIdMap = namedJdbcTemplate.query(sql, params, Meme.DEF_ROW_MAPPER)
				  .stream()
				  .collect(Collectors.toMap(Meme::getId, Function.identity()));
			
			return ids.stream()
				  .map(memeIdMap::get)
				  .collect(Collectors.toList());
		} catch (EmptyResultDataAccessException e) {
			return Collections.emptyList();
		}
	}
}
