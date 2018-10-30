package ru.kek.memehouse.controllers.api.memes;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kek.memehouse.services.interfaces.MemeService;

/**
 * gordeevnm@gmail.com
 * 20.11.17
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/memes", method = RequestMethod.DELETE)
public class DeleteMemesController {
	
	private final MemeService memeService;
	
	@RequestMapping("/{meme-id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('REGISTERED_USER')")
	public void delete(@PathVariable("meme-id") Long memeId) {
		memeService.delete(memeId);
	}
}
