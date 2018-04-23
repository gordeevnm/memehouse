package ru.kek.memehouse.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.kek.memehouse.models.Ban;
import ru.kek.memehouse.models.User;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

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
	private Long id;
	private List<String> roles;
	private String username;
	private String email;
	private Timestamp registrationTime;
	private boolean isDeleted;
	private List<Ban> futureBansList;
	private String authToken;
	
	public static UserDto from(User user) {
		return UserDto.builder()
				.id(user.getId())
				.roles(Arrays.asList(user.getRoles()))
				.username(user.getUsername())
				.email(user.getEmail())
				.registrationTime(user.getRegistrationTime())
				.isDeleted(user.isDeleted())
				.futureBansList(user.getFutureBansList())
				.build();
	}
}
