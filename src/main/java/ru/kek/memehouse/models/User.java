package ru.kek.memehouse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * gordeevnm@gmail.com
 * 05.10.17
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class User implements Serializable {
	private int id;
	private Set<GrantedAuthority> roles;
	private String username;
	private String password;
	private String email;
	private Timestamp registrationTime;
	private boolean isDeleted;
	private List<Ban> futureBansList;
	
	public static final ResultSetExtractor<User> WITH_BANS_VIEW_RS_EXTRACTOR = new WithBansViewRsExtractor();
	public static final RowMapper<User> DEF_ROW_MAPPER = new DefaultRowMapper();
	
	private static class DefaultRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			String[] rolesStringArr = (String[]) rs.getArray("roles").getArray();
			Set<GrantedAuthority> roles = Arrays.stream(rolesStringArr)
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toCollection(HashSet::new));
			
			return User.builder()
					.id(rs.getInt("id"))
					.roles(roles)
					.username(rs.getString("username"))
					.password(rs.getString("password"))
					.email(rs.getString("email"))
					.registrationTime(rs.getTimestamp("registration_time"))
					.isDeleted(rs.getBoolean("is_deleted"))
					.futureBansList(new ArrayList<>())
					.build();
		}
	}
	
	private static class WithBansViewRsExtractor implements ResultSetExtractor<User> {
		@Override
		public User extractData(ResultSet rs) throws SQLException, DataAccessException {
			if (!rs.next())
				throw new EmptyResultDataAccessException(1);
			
			String[] rolesStringArr = (String[]) rs.getArray("u_roles").getArray();
			Set<GrantedAuthority> roles = Arrays.stream(rolesStringArr)
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toCollection(HashSet::new));
			
			User user = User.builder()
					.id(rs.getInt("u_id"))
					.roles(roles)
					.username(rs.getString("u_username"))
					.password(rs.getString("u_password"))
					.email(rs.getString("u_email"))
					.registrationTime(rs.getTimestamp("u_registration_time"))
					.isDeleted(rs.getBoolean("u_is_deleted"))
					.futureBansList(new ArrayList<>())
					.build();
			
			do {
				Ban ban = Ban.builder()
						.id(rs.getInt("b_id"))
						.userId(rs.getInt("b_user_id"))
						.moderatorId(rs.getInt("b_moderator_id"))
						.startTime(rs.getTimestamp("b_start_time"))
						.endTime(rs.getTimestamp("b_end_time"))
						.reason(rs.getString("b_reason"))
						.isActive(rs.getBoolean("b_is_active"))
						.build();
				
				user.getFutureBansList().add(ban);
			} while (rs.next());
			
			return user;
		}
	}
}
