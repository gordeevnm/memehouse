package ru.kek.memehouse.services.interfaces;

import ru.kek.memehouse.dto.RegistrationDto;
import ru.kek.memehouse.dto.UserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * gordeevnm@gmail.com
 * 14.01.18
 */
public interface RegistrationService {
	UserDto registration(RegistrationDto registrationDto, HttpServletRequest request, HttpServletResponse response);
}
