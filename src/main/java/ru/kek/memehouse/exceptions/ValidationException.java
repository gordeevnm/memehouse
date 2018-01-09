package ru.kek.memehouse.exceptions;

import ru.kek.memehouse.exceptions.dto.ValidationExceptionDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * gordeevnm@gmail.com
 * 08.01.18
 */
public class ValidationException extends RuntimeException {
	private final List<ValidationExceptionDto> errors;
	
	public ValidationException(List<ValidationExceptionDto> errors) {
		this.errors = errors;
	}
	
	public ValidationException(ValidationExceptionDto error) {
		this.errors = Collections.singletonList(error);
	}
	
	public ValidationException(String field, String message) {
		this.errors = Collections.singletonList(new ValidationExceptionDto(field, message));
	}
	
	public List<ValidationExceptionDto> getErrors() {
		return errors;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder {
		private List<ValidationExceptionDto> errors;
		
		public Builder add(String field, String message) {
			this.errors.add(new ValidationExceptionDto(field, message));
			return this;
		}
		
		public Builder add(ValidationExceptionDto error) {
			this.errors.add(error);
			return this;
		}
		
		public Builder() {
			this.errors = new ArrayList<>();
		}
		
		public ValidationException build() {
			return new ValidationException(errors);
		}
	}
}
