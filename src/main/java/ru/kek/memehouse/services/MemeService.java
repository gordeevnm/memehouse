package ru.kek.memehouse.services;

import ru.kek.memehouse.configuration.security.AuthenticationToken;
import ru.kek.memehouse.models.Meme;
import ru.kek.memehouse.models.Tag;

import java.util.List; /**
 * gordeevnm@gmail.com
 * 07.10.17
 */
public interface MemeService {
	List<Meme> search(String query, String name, List<Tag> tags, int count, int offset, AuthenticationToken token);

	Meme add(Meme meme, AuthenticationToken token);

	Meme get(long memeId, AuthenticationToken token);

	Meme edit(Meme meme, AuthenticationToken token);

	void delete(long memeId, AuthenticationToken token);
}
