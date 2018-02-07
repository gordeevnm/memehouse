package ru.kek.memehouse.services.interfaces;

import ru.kek.memehouse.dto.MemeDto;
import ru.kek.memehouse.dto.SearchQuery;
import ru.kek.memehouse.models.Meme;

import java.util.List;

/**
 * gordeevnm@gmail.com
 * 07.10.17
 */
public interface MemeService {
	List<Meme> search(SearchQuery query);
	
	MemeDto add(MemeDto meme);
	
	MemeDto get(int memeId);
	
	Meme edit(int memeId, Meme meme);
	
	void delete(int memeId);
	
	void save(int memeId);
	
	void addNote(int memeId, String note);
}
