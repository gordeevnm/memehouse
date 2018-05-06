package ru.kek.memehouse.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

/**
 * gordeevnm@gmail.com
 * 12.12.17
 */
@Service
public class MemeServiceImpl implements MemeService {
	@Autowired
	private MemesDao memesDao;
	
	@Override
	public List<Meme> search(SearchQuery query) {
		throw new UnsupportedOperationException("Service not implemented");
	}
	
	@Override
	public MemeDto create(MemeModifyDto memeInfo) {
		Meme meme = memeInfo.toModel()
			  .setCreatedBy(AuthUtils.authenticatedUser())
			  .setCreateTime(new Timestamp(System.currentTimeMillis()))
			  .setDeleted(false);
		
		memesDao.create(meme);
		
		return MemeDto.from(meme);
	}
	
	@Override
	public MemeDto get(int memeId) {
		Meme meme = memesDao.findById(memeId)
//			  .filter(m -> m.isPublic() ||
//					 Objects.equals(m.getCreatedBy().getId(), AuthUtils.authenticatedUser().getId()) ||
//					 AuthUtils.currentAuthentication().getAuthorities().contains(Roles.ROLE_MEME_MODERATOR) ||
//					 AuthUtils.currentAuthentication().getAuthorities().contains(Roles.ROLE_ADMIN))
			  .orElseThrow(() -> new NotFoundException("Мем не найден"));
		
		return MemeDto.from(meme);
	}
	
	@Override
	public Meme put(int memeId, MemeModifyDto meme) {
		throw new UnsupportedOperationException("Service not implemented");
	}
	
	@Override
	public void delete(int memeId) {
		throw new UnsupportedOperationException("Service not implemented");
	}
	
	@Override
	public void save(int memeId) {
		throw new UnsupportedOperationException("Service not implemented");
	}
	
	@Override
	public void addNote(int memeId, String note) {
		throw new UnsupportedOperationException("Service not implemented");
	}
}
