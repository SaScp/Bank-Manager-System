package ru.alex.bank_managersystem.web.controlleradvice.authentication;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.alex.bank_managersystem.util.exception.*;
import ru.alex.bank_managersystem.util.exception.authentication.AccessDeniedException;
import ru.alex.bank_managersystem.util.exception.authentication.AccountHasBlockException;
import ru.alex.bank_managersystem.util.exception.authentication.LoginUserException;
import ru.alex.bank_managersystem.util.exception.authentication.RegistrationUserException;
import ru.alex.bank_managersystem.util.exception.handler.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AuthenticationControllerAdvice {

    Map<Class<? extends RuntimeException>, ExceptionHandlerStrategy> handlers;

    public AuthenticationControllerAdvice() {
        handlers = new HashMap<>();

        handlers.put(AccountHasBlockException.class, new AccountHasBlockExceptionHandler());
        handlers.put(RegistrationUserException.class, new RegistrationUserExceptionHandler());
        handlers.put(LoginUserException.class, new LoginUserExceptionHandler());
        handlers.put(AccessDeniedException.class, new AccessDeniedExceptionHandler());
        handlers.put(BadCredentialsException.class, new BadCredentialExceptionHandler());
    }

    @ExceptionHandler(
            {
                    AccountHasBlockException.class,
                    RegistrationUserException.class,
                    LoginUserException.class,
                    AccessDeniedException.class,
                    BadCredentialsException.class
            }
    )
    private ResponseEntity<ErrorResponse> exHandler(RuntimeException e) {
        ExceptionHandlerStrategy strategy = handlers.get(e.getClass());
        return ResponseEntity
                .badRequest()
                .body(strategy.handleException(e));
    }
}
