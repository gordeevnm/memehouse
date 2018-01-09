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
 * 20.11.17
 */
@RestController
@RequestMapping(value = "/api/meme", method = RequestMethod.PUT)
public class PutMemesController {
	
	private final MemeService memeService;
	
	@Autowired
	public PutMemesController(MemeService memeService) {
		this.memeService = memeService;
	}
	
	@RequestMapping("/{meme-id}/save")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('REGISTERED_USER')")
	public void save(@PathVariable("meme-id") int memeId,
	                 Authentication auth) {
		memeService.save(memeId, auth);
	}
	
	@RequestMapping("/{meme-id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAnyRole('REGISTERED_USER')")
	public Meme edit(@PathVariable("meme-id") int memeId,
	                 @RequestBody Meme meme,
	                 Authentication auth) {
		return memeService.edit(memeId, meme, auth);
	}
}
