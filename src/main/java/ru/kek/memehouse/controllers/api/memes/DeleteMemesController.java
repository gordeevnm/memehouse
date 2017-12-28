package ru.kek.memehouse.controllers.api.memes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kek.memehouse.configuration.security.AuthenticationToken;
import ru.kek.memehouse.services.MemeService;

/**
 * gordeevnm@gmail.com
 * 20.11.17
 */
@RestController
@RequestMapping(value = "/api/meme", method = RequestMethod.DELETE)
public class DeleteMemesController {

	private final MemeService memeService;

	@Autowired
	public DeleteMemesController(MemeService memeService) {
		this.memeService = memeService;
	}

	/**
	 * Удаление мема
	 *
	 * @param memeId id мема
	 * @param token  информация о авторизованном пользователе
	 */
	@RequestMapping("/{meme-id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('REGISTERED_USER')")
	public void delete(@PathVariable("meme-id") int memeId,
	                   AuthenticationToken token) {
		memeService.delete(memeId, token);
	}
}
