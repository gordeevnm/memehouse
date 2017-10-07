package ru.kek.memehouse.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.kek.memehouse.configuration.security.exceptions.TokenAuthenticationHeaderNotFound;
import ru.kek.memehouse.exceptions.BadRequestException;
import ru.kek.memehouse.models.additional.JacksonDto;

import javax.validation.ValidationException;

/**
 * gordeevnm@gmail.com
 * 06.10.17
 */
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	private static final HttpHeaders headers;

	static {
		headers = new HttpHeaders();
		headers.set("Content-Type", "application/json;charset=UTF-8");
	}

	@ExceptionHandler({AuthenticationException.class})
	public ResponseEntity<Object> incorrectAuthData(Exception e, WebRequest request) {
		return handleExceptionInternal(
				e,
				new ErrorDto(e.getMessage()),
				headers,
				HttpStatus.FORBIDDEN,
				request);
	}

	@ExceptionHandler({TokenAuthenticationHeaderNotFound.class})
	public ResponseEntity<Object> incorrectToken(Exception e, WebRequest request) {
		return handleExceptionInternal(
				e,
				new ErrorDto(e.getMessage()),
				headers,
				HttpStatus.UNAUTHORIZED,
				request);
	}

	@ExceptionHandler({BadRequestException.class, ValidationException.class})
	public ResponseEntity<Object> badRequest(Exception e, WebRequest request) {
		return handleExceptionInternal(
				e,
				new ErrorDto(e.getMessage()),
				headers,
				HttpStatus.BAD_REQUEST,
				request);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> defaultException(Exception e, WebRequest request) {
		e.printStackTrace();
		return handleExceptionInternal(
				e,
				new ErrorDto(e.getMessage()),
				headers,
				HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}

	@JacksonDto
	private static class ErrorDto {
		private String message;

		ErrorDto(String message) {
			this.message = message;
		}
	}
}
