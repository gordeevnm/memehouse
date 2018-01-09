package ru.kek.memehouse.controllers.api.memes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.kek.memehouse.models.Meme;
import ru.kek.memehouse.services.interfaces.MemeService;

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
	
	@RequestMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('REGISTERED_USER')")
	public Meme add(@RequestBody Meme meme,
	                Authentication auth) {
		return memeService.add(meme, auth);
	}
	
	@RequestMapping("/meme/{meme-id}/note")
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('REGISTERED_USER')")
	public void addNote(@PathVariable("meme-id") int memeId,
	                    @RequestBody String note,
	                    Authentication auth) {
		memeService.addNote(memeId, note, auth);
	}
}