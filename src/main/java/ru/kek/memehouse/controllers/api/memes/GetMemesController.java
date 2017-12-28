package ru.kek.memehouse.controllers.api.memes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kek.memehouse.configuration.security.AuthenticationToken;
import ru.kek.memehouse.models.Meme;
import ru.kek.memehouse.models.Tag;
import ru.kek.memehouse.services.MemeService;

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

	/**
	 * Поиск мемов
	 *
	 * @param query       запрос для поиска
	 * @param type        тип мема(только текст/изображение/видео)
	 * @param visibility  видимость мема(только открытые/только закрытые/все)
	 * @param tags        поиск по конкретным тегам
	 * @param periodStart поиск по времени добавления мема
	 * @param periodEnd   поиск по времени добавления мема
	 * @param count       количество элементов на странице
	 * @param offset      сдвиг
	 * @param byOwner     искать только по добавленным пользователем мемам
	 * @param token       информация о авторизованном пользователе
	 * @return список найденных мемов
	 */
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
	                         AuthenticationToken token) {
		return memeService.search(query, type, visibility, tags, periodStart, periodEnd, count, offset, token);
	}

	/**
	 * Получение информации о меме по его id
	 *
	 * @param memeId id мема
	 * @param token  нформация о авторизованном пользователе
	 * @return информация о меме
	 */
	@RequestMapping("/{meme-id}")
	@ResponseStatus(HttpStatus.OK)
	public Meme get(@PathVariable("meme-id") int memeId,
	                AuthenticationToken token) {
		return memeService.get(memeId, token);
	}

}
