package ru.kek.memehouse.services.interfaces;

import org.springframework.security.core.Authentication;
import ru.kek.memehouse.models.Meme;
import ru.kek.memehouse.models.Tag;

import java.sql.Timestamp;
import java.util.List;

/**
 * gordeevnm@gmail.com
 * 07.10.17
 */
public interface MemeService {
	List<Meme> search(String query, String type, String visibility, List<Tag> tags, Timestamp periodStart, Timestamp periodEnd, int count, int offset, Authentication auth);
	
	Meme add(Meme meme, Authentication auth);
	
	Meme get(int memeId, Authentication auth);
	
	Meme edit(int memeId, Meme meme, Authentication auth);
	
	void delete(int memeId, Authentication auth);
	
	void save(int memeId, Authentication auth);
	
	void addNote(int memeId, String note, Authentication auth);
}
