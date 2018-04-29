package ru.kek.memehouse.dao.impl;

import org.intellij.lang.annotations.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;
import ru.kek.memehouse.dao.interfaces.RolesDao;
import ru.kek.memehouse.models.User;

import java.util.HashMap;
import java.util.Map;

/**
 * gordeevnm@gmail.com
 * 14.01.18
 */
@Repository
public class RolesDaoJdbcImpl implements RolesDao {
	@Language("PostgreSQL")
	private static final String GRANT_ROLE_SQL =
		  "UPDATE public.user " +
				 "SET roles = array_append(roles, 'role3') " +
				 "WHERE id = :userId " +
				 "   AND NOT roles @> ARRAY[:role]::VARCHAR[]";
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@Override
	public void grantRole(GrantedAuthority role, User user) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", user.getId());
		params.put("role", role);
		
		namedJdbcTemplate.update(GRANT_ROLE_SQL, params);
		user.addRole(role);
	}
}
