package ru.kek.memehouse.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import ru.kek.memehouse.dao.elastic.MemesElasticRepo;
import ru.kek.memehouse.dao.interfaces.MemesDao;
import ru.kek.memehouse.dto.MemeDto;
import ru.kek.memehouse.dto.MemeModifyDto;
import ru.kek.memehouse.dto.SearchQuery;
import ru.kek.memehouse.exceptions.NotFoundException;
import ru.kek.memehouse.models.Meme;
import ru.kek.memehouse.services.AuthUtils;
import ru.kek.memehouse.services.interfaces.MemeService;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.disMaxQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

/**
 * gordeevnm@gmail.com
 * 12.12.17
 */
@Service
@RequiredArgsConstructor
public class MemeServiceImpl implements MemeService {
	private final MemesDao memesDao;
	private final ElasticsearchTemplate elasticsearchTemplate;
	private final MemesElasticRepo memesElasticRepo;
	
	@Override
	public List<Meme> search(SearchQuery query) {
		throw new UnsupportedOperationException("Service not implemented");
	}
	
	@Override
	public MemeDto create(MemeModifyDto memeInfo) {
		Meme meme = memeInfo.toModel()
			  .setCreatedByUser(AuthUtils.authenticatedUser())
			  .setCreatedById(AuthUtils.authenticatedUser().getId())
			  .setCreateTime(new Timestamp(System.currentTimeMillis()))
			  .setDeleted(false);
		
		memesDao.create(meme);
		memesElasticRepo.save(meme);
		
		return MemeDto.from(meme);
	}
	
	@Override
	public List<MemeDto> create(List<MemeModifyDto> memeInfo) {
		final List<Meme> memes = memeInfo.stream()
			  .map(memeModifyDto -> memeModifyDto.toModel()
					 .setCreatedByUser(AuthUtils.authenticatedUser())
					 .setCreatedById(AuthUtils.authenticatedUser().getId())
					 .setCreateTime(new Timestamp(System.currentTimeMillis()))
					 .setDeleted(false))
			  .peek(memesDao::create)
			  .collect(Collectors.toList());
		
		memesElasticRepo.saveAll(memes);
		
		return memes.stream()
			  .map(MemeDto::from)
			  .collect(Collectors.toList());
	}
	
	@Override
	public MemeDto get(Long memeId) {
		Meme meme = memesDao.findById(memeId)
//			  .filter(m -> m.isPublic() ||
//					 Objects.equals(m.getCreatedBy().getId(), AuthUtils.authenticatedUser().getId()) ||
//					 AuthUtils.currentAuthentication().getAuthorities().contains(Roles.ROLE_MEME_MODERATOR) ||
//					 AuthUtils.currentAuthentication().getAuthorities().contains(Roles.ROLE_ADMIN))
			  .orElseThrow(() -> new NotFoundException("Мем не найден"));
		
		return MemeDto.from(meme);
	}
	
	@Override
	public MemeDto put(Long memeId, MemeModifyDto memeInfo) {
		Meme meme = memesDao.findById(memeId)
			  .orElseThrow(() -> new NotFoundException("meme " + memeId + " not found"));
		
		meme.setTags(memeInfo.getTags())
			  .setName(memeInfo.getName())
			  .setDescription(memeInfo.getDescription())
			  .setPictureId(memeInfo.getPictureId())
			  .setLurkmoreLink(memeInfo.getLurkmoreLink())
			  .setPublic(memeInfo.isPublic());
		
		memesDao.update(meme, AuthUtils.authenticatedUser().getId());
		
		return MemeDto.from(meme);
	}
	
	@Override
	public void delete(Long memeId) {
		throw new UnsupportedOperationException("Service not implemented");
	}
	
	@Override
	public void save(Long memeId) {
		throw new UnsupportedOperationException("Service not implemented");
	}
	
	@Override
	public void addNote(Long memeId, String note) {
		throw new UnsupportedOperationException("Service not implemented");
	}
	
	@Override
	public List<Meme> simpleSearch(String text) {
		org.springframework.data.elasticsearch.core.query.SearchQuery query = new NativeSearchQueryBuilder()
			  .withQuery(
					 disMaxQuery()
							.add(matchQuery("name", text).boost(2).fuzziness("auto"))
							.add(matchQuery("description", text).boost(1).fuzziness("auto"))
							.add(matchQuery("tags", text))
			  )
			  .withFields("id")
			  .build();
		
		final List<Long> ids = elasticsearchTemplate.queryForIds(query)
			  .stream()
			  .map(Long::parseLong)
			  .collect(Collectors.toList());
		return memesDao.findAllById(ids);
	}
}
