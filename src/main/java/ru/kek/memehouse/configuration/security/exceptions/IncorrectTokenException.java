package ru.kek.memehouse.configuration.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class IncorrectTokenException extends AuthenticationException {
    public IncorrectTokenException(String msg) {
        super(msg);
    }
}
