package ru.kek.memehouse.controllers.api.memes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kek.memehouse.dto.MemeDto;
import ru.kek.memehouse.dto.SearchQuery;
import ru.kek.memehouse.models.Meme;
import ru.kek.memehouse.services.interfaces.MemeService;

import javax.validation.Valid;
import java.util.List;

/**
 * gordeevnm@gmail.com
 * 20.11.17
 */
@RestController
@RequestMapping(value = "/api/meme", method = RequestMethod.GET)
public class GetMemesController {
	
	private final MemeService memeService;
	
	@Autowired
	public GetMemesController(MemeService memeService) {
		this.memeService = memeService;
	}
	
	@RequestMapping("/search")
	@ResponseStatus(HttpStatus.OK)
	public List<Meme> search(@Valid SearchQuery query) {
		return memeService.search(query);
	}
	
	@RequestMapping("/{meme-id}")
	@ResponseStatus(HttpStatus.OK)
	public MemeDto get(@PathVariable("meme-id") int memeId) {
		return memeService.get(memeId);
	}
	
}
