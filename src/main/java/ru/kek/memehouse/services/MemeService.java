package ru.kek.memehouse.services;

import ru.kek.memehouse.configuration.security.AuthenticationToken;
import ru.kek.memehouse.models.Meme;
import ru.kek.memehouse.models.Tag;

import java.sql.Timestamp;
import java.util.List; /**
 * gordeevnm@gmail.com
 * 07.10.17
 */
public interface MemeService {
	List<Meme> search(String query, String type, String visibility, List<Tag> tags, Timestamp periodStart, Timestamp periodEnd, int count, int offset, AuthenticationToken token);

	Meme add(Meme meme, AuthenticationToken token);

	Meme get(int memeId, AuthenticationToken token);

	Meme edit(int memeId, Meme meme, AuthenticationToken token);

	void delete(int memeId, AuthenticationToken token);

	void save(int memeId, AuthenticationToken token);

	void addNote(int memeId, String note, AuthenticationToken token);
}
