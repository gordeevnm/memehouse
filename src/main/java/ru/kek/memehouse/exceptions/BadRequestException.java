package ru.kek.memehouse.exceptions;

/**
 * gordeevnm@gmail.com
 * 06.10.17
 */
public class BadRequestException extends RuntimeException {
	public BadRequestException(String message) {
		super(message);
	}
}
