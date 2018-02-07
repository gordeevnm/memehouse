package ru.kek.memehouse.dao.interfaces;

import org.springframework.security.core.GrantedAuthority;
import ru.kek.memehouse.models.User;

/**
 * gordeevnm@gmail.com
 * 14.01.18
 */
public interface RolesDao {
	void grantRole(GrantedAuthority role, User user);
}
