package ru.kek.memehouse.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

import java.sql.Timestamp;
import java.util.List;

/**
 * gordeevnm@gmail.com
 * 05.10.17
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
public class User {
	private int id;
	private List<GrantedAuthority> roles;
	private String username;
	private String password;
	private String email;
	private Timestamp registrationTime;
	private boolean isDeleted;
	private Ban currentBan;
}
