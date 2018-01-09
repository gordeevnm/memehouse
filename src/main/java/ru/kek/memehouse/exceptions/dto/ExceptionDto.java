package ru.kek.memehouse.exceptions.dto;

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
	private T data;
}
