package ru.kek.memehouse.services.impl;

import org.springframework.stereotype.Service;
import ru.kek.memehouse.configuration.security.AuthenticationToken;
import ru.kek.memehouse.models.Meme;
import ru.kek.memehouse.models.Tag;
import ru.kek.memehouse.services.MemeService;

import java.sql.Timestamp;
import java.util.List;

/**
 * gordeevnm@gmail.com
 * 12.12.17
 */
@Service
public class MemeServiceImpl implements MemeService {
	@Override
	public List<Meme> search(String query, String type, String visibility, List<Tag> tags, Timestamp periodStart, Timestamp periodEnd, int count, int offset, AuthenticationToken token) {
		throw new UnsupportedOperationException("Service not implemented");
	}
	
	@Override
	public Meme add(Meme meme, AuthenticationToken token) {
	
	}
	
	@Override
	public Meme get(int memeId, AuthenticationToken token) {
		throw new UnsupportedOperationException("Service not implemented");
	}
	
	@Override
	public Meme edit(int memeId, Meme meme, AuthenticationToken token) {
		throw new UnsupportedOperationException("Service not implemented");
	}
	
	@Override
	public void delete(int memeId, AuthenticationToken token) {
		throw new UnsupportedOperationException("Service not implemented");
	}
	
	@Override
	public void save(int memeId, AuthenticationToken token) {
		throw new UnsupportedOperationException("Service not implemented");
	}
	
	@Override
	public void addNote(int memeId, String note, AuthenticationToken token) {
		throw new UnsupportedOperationException("Service not implemented");
	}
}
