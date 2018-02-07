package ru.kek.memehouse.exceptions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * gordeevnm@gmail.com
 * 08.01.18
 */
@Data
@Builder
@AllArgsConstructor
public class ExceptionDto<T> {
	private String cause;
	private String message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T data;
}
