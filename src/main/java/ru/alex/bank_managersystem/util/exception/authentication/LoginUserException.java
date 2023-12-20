package ru.alex.bank_managersystem.util.exception.authentication;

import org.springframework.security.core.AuthenticationException;

public class LoginUserException extends AuthenticationException {
    public LoginUserException(String msg) {
        super(msg);
    }
}
