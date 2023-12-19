package ru.alex.bank_managersystem.util.exception.handler;

import ru.alex.bank_managersystem.util.exception.ErrorResponse;

import java.time.ZonedDateTime;

public class LoginUserExceptionHandler implements ExceptionHandlerStrategy {
    @Override
    public ErrorResponse handleException(RuntimeException exception) {
        return ErrorResponse.builder()
                .error("401")
                .msg(exception.getMessage())
                .dateTime(ZonedDateTime.now())
                .build();
    }
}
