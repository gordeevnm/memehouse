package ru.kek.memehouse.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.kek.memehouse.exceptions.*;
import ru.kek.memehouse.exceptions.dto.ExceptionDto;
import ru.kek.memehouse.exceptions.dto.ValidationExceptionDto;

import java.util.List;
import java.util.stream.Collectors;

/**
 * gordeevnm@gmail.com
 * 08.01.18
 */
@ControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler({BindException.class})
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionDto<List<ValidationExceptionDto>> validationExceptionHandler(BindException e) {
		System.err.println("BindException");
		return ExceptionDto.<List<ValidationExceptionDto>>builder()
			  .cause("ValidationException")
			  .data(e.getFieldErrors().stream()
					  .map(fieldError -> new ValidationExceptionDto(fieldError.getField(), fieldError.getDefaultMessage()))
					  .collect(Collectors.toList()))
			  .build();
	}
	
	@ExceptionHandler({ValidationException.class})
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionDto<List<ValidationExceptionDto>> validationExceptionHandler(ValidationException e) {
		System.err.println("BindException");
		return ExceptionDto.<List<ValidationExceptionDto>>builder()
			  .cause("ValidationException")
			  .data(e.getErrors())
			  .build();
	}
	
	@ExceptionHandler({BadRequestException.class})
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionDto<String> badRequestHandler(BadRequestException e) {
		return ExceptionDto.<String>builder()
			  .cause("BadRequestException")
			  .message(e.getMessage())
			  .build();
	}
	
	@ExceptionHandler({NotFoundException.class})
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionDto<String> notFoundHandler(NotFoundException e) {
		return ExceptionDto.<String>builder()
			  .cause("NotFoundException")
			  .message(e.getMessage())
			  .build();
	}
	
	@ExceptionHandler({ForbiddenException.class})
	@ResponseBody
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public ExceptionDto<String> forbiddenHandler(ForbiddenException e) {
		return ExceptionDto.<String>builder()
			  .cause("ForbiddenException")
			  .message(e.getMessage())
			  .build();
	}
	
	@ExceptionHandler({UnauthorizedException.class})
	@ResponseBody
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ExceptionDto<String> unauthorizedHandler(UnauthorizedException e) {
		return ExceptionDto.<String>builder()
			  .cause("UnauthorizedException")
			  .message(e.getMessage())
			  .build();
	}
}
