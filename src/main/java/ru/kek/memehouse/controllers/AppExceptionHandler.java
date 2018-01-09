package ru.kek.memehouse.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.kek.memehouse.exceptions.BadRequestException;
import ru.kek.memehouse.exceptions.ValidationException;
import ru.kek.memehouse.exceptions.dto.ExceptionDto;
import ru.kek.memehouse.exceptions.dto.ValidationExceptionDto;

import java.util.ArrayList;
import java.util.List;

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
		System.out.println("BindException");
		List<ValidationExceptionDto> result = new ArrayList<>();
		e.getFieldErrors().forEach(fieldError -> result.add(
				new ValidationExceptionDto(
						fieldError.getField(),
						fieldError.getDefaultMessage()
				)
		));
		
		return ExceptionDto.<List<ValidationExceptionDto>>builder()
				.cause("ValidationException")
				.data(result)
				.build();
	}
	
	@ExceptionHandler({ValidationException.class})
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionDto<List<ValidationExceptionDto>> validationExceptionHandler(ValidationException e) {
		System.out.println("BindException");
		List<ValidationExceptionDto> result = new ArrayList<>();
		e.getErrors().forEach(exceptionDto -> result.add(
				new ValidationExceptionDto(
						exceptionDto.getField(),
						exceptionDto.getMessage()
				)
		));
		
		return ExceptionDto.<List<ValidationExceptionDto>>builder()
				.cause("ValidationException")
				.data(result)
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
}
