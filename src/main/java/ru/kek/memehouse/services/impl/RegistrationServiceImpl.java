package ru.kek.memehouse.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Collections;
import java.util.HashSet;

/**
 * gordeevnm@gmail.com
 * 14.01.18
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {
	@Autowired
	private UsersDao usersDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDto registration(RegistrationDto registrationDto, HttpServletRequest request, HttpServletResponse response) {
		User user = User.builder()
				.username(registrationDto.getUsername())
				.password(passwordEncoder.encode(registrationDto.getPassword()))
				.email(registrationDto.getEmail())
				.registrationTime(new Timestamp(System.currentTimeMillis()))
				.roles(new HashSet<>(Collections.singletonList(Roles.ROLE_REGISTERED_USER)))
				.isDeleted(false)
				.build();
		
		usersDao.create(user);
		
		return UserDto.from(user);
	}
}