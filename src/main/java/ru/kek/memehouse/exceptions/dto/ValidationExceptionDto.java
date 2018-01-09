package ru.kek.memehouse.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * gordeevnm@gmail.com
 * 08.01.18
 */
@Data
@AllArgsConstructor
public class ValidationExceptionDto {
	private String field;
	private String message;
}
