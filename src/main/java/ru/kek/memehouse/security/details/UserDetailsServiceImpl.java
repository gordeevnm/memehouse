package ru.kek.memehouse.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kek.memehouse.dao.interfaces.UsersDao;
import ru.kek.memehouse.models.User;

@Primary
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UsersDao usersDao;
	
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		return usersDao.findByUsername(username).orElseThrow(
			  () -> new UsernameNotFoundException("User not found by username " + username));
	}
}