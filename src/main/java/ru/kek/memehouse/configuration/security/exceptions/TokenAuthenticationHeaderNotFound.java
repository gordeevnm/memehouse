package ru.kek.memehouse.configuration.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class TokenAuthenticationHeaderNotFound extends AuthenticationException {
    public TokenAuthenticationHeaderNotFound(String msg) {
        super(msg);
    }
}
