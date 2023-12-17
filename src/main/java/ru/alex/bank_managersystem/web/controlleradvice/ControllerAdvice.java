package ru.alex.bank_managersystem.web.controlleradvice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.alex.bank_managersystem.util.exception.*;
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
        handlers.put(AccountHasBlockException.class, new AccountHasBlockExceptionHandler());
        handlers.put(RegistrationUserException.class, new RegistrationUserExceptionHandler());
        handlers.put(MoneyAccountNotFoundException.class, new MoneyAccountNotFoundExceptionHandler());
        handlers.put(LoginUserException.class, new LoginUserExceptionHandler());
        handlers.put(InsufficientMoneysException.class, new InsufficientMoneysExceptionHandler());
        handlers.put(CreditHistoryIsEmptyException.class, new CreditHistoryIsEmptyExceptionHandler());
        handlers.put(CardValidatorException.class, new CardValidatorExceptionHandler());
        handlers.put(AccountsIsEmptyException.class, new AccountsIsEmptyExceptionHandler());
        handlers.put(AccessDeniedException.class, new AccessDeniedExceptionHandler());
    }
    @ExceptionHandler(
            {
                    UserNotFoundException.class,
                    ResourceNotFoundException.class,
                    AccountHasBlockException.class,
                    RegistrationUserException.class,
                    MoneyAccountNotFoundException.class,
                    LoginUserException.class,
                    InsufficientMoneysException.class,
                    CreditHistoryIsEmptyException.class,
                    CardValidatorException.class,
                    AccountsIsEmptyException.class,
                    AccessDeniedException.class,
            }
    )
    private ResponseEntity<ErrorResponse> exHandler(RuntimeException e) {
        ExceptionHandlerStrategy strategy = handlers.get(e.getClass());
        return ResponseEntity
                .badRequest()
                .body(strategy.handleException(e));
    }
}
