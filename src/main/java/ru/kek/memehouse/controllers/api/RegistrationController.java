package ru.kek.memehouse.controllers.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kek.memehouse.dto.RegistrationDto;
import ru.kek.memehouse.dto.UserDto;
import ru.kek.memehouse.services.interfaces.RegistrationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * gordeevnm@gmail.com
 * 07.10.17
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RegistrationController {
	private final RegistrationService registrationService;
	

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public UserDto registration(@Valid @RequestBody RegistrationDto registrationDto,
	                            HttpServletRequest request,
	                            HttpServletResponse response) {
		return registrationService.registration(registrationDto, request, response);
	}
}
