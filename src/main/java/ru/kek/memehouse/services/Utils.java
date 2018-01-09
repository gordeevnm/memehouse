package ru.kek.memehouse.services;

import org.springframework.security.core.Authentication;
import ru.kek.memehouse.configuration.security.details.UserDetailsImpl;
import ru.kek.memehouse.exceptions.UnauthorizedException;

/**
 * gordeevnm@gmail.com
 * 09.12.17
 */
public class Utils {
	// TODO: 08.01.18 найти более подходящее место
	public static final String USERNAME_FULL_REGEX = "^(?i:(?!id[0-9OIil]*$)[a-z]+[a-z._0-9]*)$";
	public static final String PHONE_REGEX = "^(\\+7|7|8)[0-9]{10}$";
	public static final String PASSWORD_REGEX = "^.{3,}$";
	public static final String EMAIL_REGEX = ".*@.*";
	public static final String USERNAME_FOR_UPDATING_REGEX = "^[a-z]+[a-z._0-9]*$";
	
	public static int getUserId(Authentication authentication) {
		if (authentication == null) {
			throw new UnauthorizedException("Пользователь не авторизован");
		}
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		return userDetails.getId();
	}
}
