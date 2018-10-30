package ru.kek.memehouse.controllers.api.memes;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping(value = "/api/memes", method = RequestMethod.PUT)
public class PutMemesController {
	
	private final MemeService memeService;
	
	@RequestMapping("/{meme-id}")
	@ResponseStatus(HttpStatus.OK)
//	@PreAuthorize("hasAnyRole('REGISTERED_USER')")
	public MemeDto put(@PathVariable("meme-id") Long memeId,
	                   @RequestBody MemeModifyDto memeInfo) {
		return memeService.put(memeId, memeInfo);
	}
}
