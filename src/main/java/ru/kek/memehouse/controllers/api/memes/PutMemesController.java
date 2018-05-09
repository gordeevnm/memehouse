package ru.kek.memehouse.controllers.api.memes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kek.memehouse.dto.MemeDto;
import ru.kek.memehouse.dto.MemeModifyDto;
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
	
	@RequestMapping("/{meme-id}")
	@ResponseStatus(HttpStatus.OK)
//	@PreAuthorize("hasAnyRole('REGISTERED_USER')")
	public MemeDto put(@PathVariable("meme-id") int memeId,
	                   @RequestBody MemeModifyDto memeInfo) {
		return memeService.put(memeId, memeInfo);
	}
}
