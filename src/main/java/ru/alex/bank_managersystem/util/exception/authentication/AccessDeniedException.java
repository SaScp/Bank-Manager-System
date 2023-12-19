package ru.alex.bank_managersystem.util.exception.authentication;

import org.springframework.security.core.AuthenticationException;

public class AccessDeniedException extends AuthenticationException {
    public AccessDeniedException(String msg) {
        super(msg);
    }
}
