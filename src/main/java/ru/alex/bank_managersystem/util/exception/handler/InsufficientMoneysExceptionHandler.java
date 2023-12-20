package ru.alex.bank_managersystem.util.exception.handler;

import ru.alex.bank_managersystem.util.exception.ErrorResponse;

import java.time.ZonedDateTime;

public class InsufficientMoneysExceptionHandler implements ExceptionHandlerStrategy {
    @Override
    public ErrorResponse handleException(RuntimeException exception) {
        return ErrorResponse.builder()
                .error("402")
                .msg(exception.getMessage())
                .dateTime(ZonedDateTime.now())
                .build();
    }
}
