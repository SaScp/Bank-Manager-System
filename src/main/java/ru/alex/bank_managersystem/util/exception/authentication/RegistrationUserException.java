package ru.alex.bank_managersystem.util.exception.authentication;

import org.springframework.security.core.AuthenticationException;

public class RegistrationUserException extends AuthenticationException {
    public RegistrationUserException(String message) {
        super(message);
    }
}
