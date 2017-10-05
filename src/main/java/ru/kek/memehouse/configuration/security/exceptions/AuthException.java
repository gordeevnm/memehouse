package ru.kek.memehouse.configuration.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class AuthException extends AuthenticationException {
    public AuthException(String msg) {
        super(msg);
    }
}
