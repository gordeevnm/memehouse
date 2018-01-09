package ru.kek.memehouse.controllers.api.memes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.kek.memehouse.models.Meme;
import ru.kek.memehouse.models.Tag;
import ru.kek.memehouse.services.interfaces.MemeService;

import java.sql.Timestamp;
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
	public List<Meme> search(@RequestParam(value = "description", required = false) String query,
	                         @RequestParam(value = "type", required = false) String type,
	                         @RequestParam(value = "visibility", required = false, defaultValue = "all") String visibility,
	                         @RequestParam(value = "tags", required = false) List<Tag> tags,
	                         @RequestParam(value = "periodStart", required = false) Timestamp periodStart,
	                         @RequestParam(value = "periodEnd", required = false) Timestamp periodEnd,
	                         @RequestParam(value = "count", required = false, defaultValue = "20") int count,
	                         @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
	                         @RequestParam(value = "byOwner", required = false, defaultValue = "false") boolean byOwner,
	                         Authentication auth) {
		return memeService.search(query, type, visibility, tags, periodStart, periodEnd, count, offset, auth);
	}
	
	/**
	 * Получение информации о меме по его id
	 *
	 * @param memeId id мема
	 * @param auth   нформация о авторизованном пользователе
	 * @return информация о меме
	 */
	@RequestMapping("/{meme-id}")
	@ResponseStatus(HttpStatus.OK)
	public Meme get(@PathVariable("meme-id") int memeId,
	                Authentication auth) {
		return memeService.get(memeId, auth);
	}
	
}
