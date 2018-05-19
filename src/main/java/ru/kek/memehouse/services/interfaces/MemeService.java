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
	
	MemeDto get(Long memeId);
	
	MemeDto put(Long memeId, MemeModifyDto meme);
	
	void delete(Long memeId);
	
	void save(Long memeId);
	
	void addNote(Long memeId, String note);
	
	List<Meme> simpleSearch(String query);
	
	List<MemeDto> create(List<MemeModifyDto> memeInfo);
}
