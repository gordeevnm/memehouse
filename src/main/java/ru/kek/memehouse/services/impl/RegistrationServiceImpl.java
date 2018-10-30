package ru.kek.memehouse.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kek.memehouse.dao.interfaces.UsersDao;
import ru.kek.memehouse.dto.RegistrationDto;
import ru.kek.memehouse.dto.UserDto;
import ru.kek.memehouse.models.Roles;
import ru.kek.memehouse.models.User;
import ru.kek.memehouse.services.interfaces.RegistrationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 * gordeevnm@gmail.com
 * 14.01.18
 */
@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
	private final UsersDao usersDao;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public UserDto registration(RegistrationDto registrationDto, HttpServletRequest request, HttpServletResponse response) {
		User user = User.builder()
			  .username(registrationDto.getUsername())
			  .password(passwordEncoder.encode(registrationDto.getPassword()))
			  .email(registrationDto.getEmail())
			  .registrationTime(new Timestamp(System.currentTimeMillis()))
			  .roles(new String[]{Roles.ROLE_REGISTERED_USER.getAuthority()})
			  .isDeleted(false)
			  .build();
		
		usersDao.create(user);
		
		return UserDto.from(user);
	}
}