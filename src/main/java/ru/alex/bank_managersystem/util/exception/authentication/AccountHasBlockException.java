package ru.alex.bank_managersystem.util.exception.authentication;

import org.springframework.security.core.AuthenticationException;

public class AccountHasBlockException extends AuthenticationException {
    public AccountHasBlockException(String msg) {
        super(msg);
    }
}
