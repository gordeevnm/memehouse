package ru.kek.memehouse.exceptions;

/**
 * gordeevnm@gmail.com
 * 30.12.17
 */
public class UnauthorizedException extends RuntimeException {
	public UnauthorizedException(String message) {
		super(message);
	}
}
