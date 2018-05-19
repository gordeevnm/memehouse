package ru.kek.memehouse.controllers.api.memes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kek.memehouse.dto.MemeDto;
import ru.kek.memehouse.dto.MemeModifyDto;
import ru.kek.memehouse.services.interfaces.MemeService;

import javax.validation.Valid;
import java.util.List;

/**
 * gordeevnm@gmail.com
 * 07.10.17
 */
@RestController
@RequestMapping(value = "/api/memes", method = RequestMethod.POST)
public class PostMemesController {
	
	private final MemeService memeService;
	
	@Autowired
	public PostMemesController(MemeService memeService) {
		this.memeService = memeService;
	}
	
	@RequestMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
//	@PreAuthorize("hasRole('REGISTERED_USER')")
	public MemeDto create(@Valid @RequestBody MemeModifyDto memeInfo) {
		return memeService.create(memeInfo);
	}
	
	@RequestMapping("/batch")
	@ResponseStatus(HttpStatus.CREATED)
//	@PreAuthorize("hasRole('REGISTERED_USER')")
	public List<MemeDto> batchCreate(@Valid @RequestBody List<MemeModifyDto> memeInfo) {
		return memeService.create(memeInfo);
	}
}
