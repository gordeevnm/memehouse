package ru.kek.memehouse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kek.memehouse.configuration.security.AuthenticationToken;
import ru.kek.memehouse.models.Meme;
import ru.kek.memehouse.models.Tag;
import ru.kek.memehouse.services.MemeService;

import java.util.List;

/**
 * gordeevnm@gmail.com
 * 07.10.17
 */
@RestController
@RequestMapping("/api/meme")
public class MemesController {

	private final MemeService memeService;

	@Autowired
	public MemesController(MemeService memeService) {
		this.memeService = memeService;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Meme add(@RequestBody Meme meme,
	                AuthenticationToken token) {
		return memeService.add(meme, token);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Meme> search(@RequestParam(value = "description", required = false) String query,
	                         @RequestParam(value = "name", required = false) String name,
	                         @RequestParam(value = "tags", required = false) List<Tag> tags,
	                         @RequestParam(value = "count", required = false, defaultValue = "20") int count,
	                         @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
	                         AuthenticationToken token) {
		return memeService.search(query, name, tags, count, offset, token);
	}

	@RequestMapping(value = "/{meme-id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Meme get(@PathVariable("meme-id") long memeId,
	                AuthenticationToken token) {
		return memeService.get(memeId, token);
	}

	@RequestMapping(value = "/{meme-id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public Meme edit(@RequestBody Meme meme,
	                 AuthenticationToken token) {
		return memeService.edit(meme, token);
	}

	@RequestMapping(value = "/{meme-id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("meme-id") long memeId,
	                   AuthenticationToken token) {
		memeService.delete(memeId, token);
	}
}
