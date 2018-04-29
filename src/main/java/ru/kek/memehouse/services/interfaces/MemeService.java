package ru.kek.memehouse.services.interfaces;

import ru.kek.memehouse.dto.MemeDto;
import ru.kek.memehouse.dto.MemeModifyDto;
import ru.kek.memehouse.dto.SearchQuery;
import ru.kek.memehouse.models.Meme;

import java.util.List;

/**
 * gordeevnm@gmail.com
 * 07.10.17
 */
public interface MemeService {
	List<Meme> search(SearchQuery query);
	
	MemeDto create(MemeModifyDto meme);
	
	MemeDto get(int memeId);
	
	Meme put(int memeId, MemeModifyDto meme);
	
	void delete(int memeId);
	
	void save(int memeId);
	
	void addNote(int memeId, String note);
}
