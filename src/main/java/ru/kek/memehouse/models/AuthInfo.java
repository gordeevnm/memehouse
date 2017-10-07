package ru.kek.memehouse.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.kek.memehouse.models.additional.JacksonDto;

/**
 * gordeevnm@gmail.com
 * 07.10.17
 */
@JacksonDto
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthInfo {
	public User userInfo;
	public String password;
	public String authToken;
}
