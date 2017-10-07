package ru.kek.memehouse.services.impl;

import org.springframework.stereotype.Service;
import ru.kek.memehouse.configuration.security.AuthenticationToken;
import ru.kek.memehouse.models.Meme;
import ru.kek.memehouse.models.Tag;
import ru.kek.memehouse.services.MemeService;

import java.util.List;

/**
 * gordeevnm@gmail.com
 * 07.10.17
 */
@Service
public class MemeServiceImpl implements MemeService {
	@Override
	public List<Meme> search(String query, String name, List<Tag> tags, int count, int offset, AuthenticationToken token) {
		return null;
	}

	@Override
	public Meme add(Meme meme, AuthenticationToken token) {
		return null;
	}

	@Override
	public Meme get(long memeId, AuthenticationToken token) {
		return null;
	}

	@Override
	public Meme edit(Meme meme, AuthenticationToken token) {
		return null;
	}

	@Override
	public void delete(long memeId, AuthenticationToken token) {

	}
}
