package ru.alex.bank_managersystem.web.controlleradvice;

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
public class ControllerAdvice {

    Map<Class<? extends RuntimeException>, ExceptionHandlerStrategy> handlers;

    public ControllerAdvice() {
        handlers = new HashMap<>();
        handlers.put(UserNotFoundException.class, new UserNotFoundExceptionHandler());
        handlers.put(ResourceNotFoundException.class, new ResourceNotFoundExceptionHandler());
        handlers.put(MoneyAccountNotFoundException.class, new MoneyAccountNotFoundExceptionHandler());
        handlers.put(InsufficientMoneysException.class, new InsufficientMoneysExceptionHandler());
        handlers.put(CardValidatorException.class, new CardValidatorExceptionHandler());
        handlers.put(AccountsIsEmptyException.class, new AccountsIsEmptyExceptionHandler());
    }

    @ExceptionHandler(
            {
                    UserNotFoundException.class,
                    ResourceNotFoundException.class,
                    MoneyAccountNotFoundException.class,
                    InsufficientMoneysException.class,
                    CardValidatorException.class,
                    AccountsIsEmptyException.class,
            }
    )
    private ResponseEntity<ErrorResponse> exHandler(RuntimeException e) {
        ExceptionHandlerStrategy strategy = handlers.get(e.getClass());
        return ResponseEntity
                .badRequest()
                .body(strategy.handleException(e));
    }
}
