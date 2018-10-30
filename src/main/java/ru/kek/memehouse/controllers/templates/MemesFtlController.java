package ru.kek.memehouse.controllers.templates;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kek.memehouse.dto.MemeDto;
import ru.kek.memehouse.services.interfaces.MemeService;

/**
 * gordeevnm@gmail.com
 * 19.05.18
 */
@Controller
@RequiredArgsConstructor
public class MemesFtlController {
	private final MemeService memeService;
	
	@RequestMapping("/memes/{meme-id}")
	public String get(@PathVariable("meme-id") Long memeId, Model model) {
		MemeDto meme = memeService.get(memeId);
		model.addAttribute("meme", meme);
		return "meme";
	}
	
	@RequestMapping("/memes/{meme-id}/edit")
	public String edit(@PathVariable("meme-id") Long memeId, Model model) {
		MemeDto meme = memeService.get(memeId);
		model.addAttribute("meme", meme);
		return "edit";
	}
}
