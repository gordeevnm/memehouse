package ru.kek.memehouse.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kek.memehouse.dao.interfaces.UsersDao;
import ru.kek.memehouse.models.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UsersDao usersDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = usersDao.findWithFutureBansByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User not found by username " + username));
		
		return new UserDetailsImpl(user);
	}
}