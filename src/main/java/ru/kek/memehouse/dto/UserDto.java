package ru.kek.memehouse.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import ru.kek.memehouse.models.Ban;
import ru.kek.memehouse.models.User;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * gordeevnm@gmail.com
 * 14.01.18
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@JsonAutoDetect(
		fieldVisibility = JsonAutoDetect.Visibility.ANY,
		getterVisibility = JsonAutoDetect.Visibility.NONE,
		setterVisibility = JsonAutoDetect.Visibility.NONE,
		creatorVisibility = JsonAutoDetect.Visibility.NONE
)
public class UserDto {
	private int id;
	private Set<String> roles;
	private String username;
	private String email;
	private Timestamp registrationTime;
	private boolean isDeleted;
	private List<Ban> futureBansList;
	private String authToken;
	
	public static UserDto from(User user) {
		return UserDto.builder()
				.id(user.getId())
				.roles(toStringSet(user.getRoles()))
				.username(user.getUsername())
				.email(user.getEmail())
				.registrationTime(user.getRegistrationTime())
				.isDeleted(user.isDeleted())
				.futureBansList(user.getFutureBansList() != null ? user.getFutureBansList() : Collections.emptyList())
				.build();
	}
	
	private static Set<String> toStringSet(Set<GrantedAuthority> roles) {
		return roles.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toSet());
	}
}
