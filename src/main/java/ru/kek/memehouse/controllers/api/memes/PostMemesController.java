package ru.kek.memehouse.controllers.api.memes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kek.memehouse.configuration.security.AuthenticationToken;
import ru.kek.memehouse.models.Meme;
import ru.kek.memehouse.services.MemeService;

/**
 * gordeevnm@gmail.com
 * 07.10.17
 */
@RestController
@RequestMapping(value = "/api/meme", method = RequestMethod.POST)
public class PostMemesController {

	private final MemeService memeService;

	@Autowired
	public PostMemesController(MemeService memeService) {
		this.memeService = memeService;
	}

	/**
	 * Создание мема
	 *
	 * @param meme  информация о загружаемом меме
	 * @param token информация о авторизованном пользователе
	 * @return информация о созданном меме
	 */
	@RequestMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('REGISTERED_USER')")
	public Meme add(@RequestBody Meme meme,
	                AuthenticationToken token) {
		return memeService.add(meme, token);
	}

	/**
	 * Добавить заметку к мему
	 * Новая заметка удаляет старую
	 *
	 * @param memeId id мема
	 * @param note   текст заметки
	 * @param token  информация о авторизованном пользователе
	 */
	@RequestMapping("/meme/{meme-id}/note")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('REGISTERED_USER')")
	public void addNote(@PathVariable("meme-id") int memeId,
	                    @RequestBody String note,
	                    AuthenticationToken token) {
		memeService.addNote(memeId, note, token);
	}
}
