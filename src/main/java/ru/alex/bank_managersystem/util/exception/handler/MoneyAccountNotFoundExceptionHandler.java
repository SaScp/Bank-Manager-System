package ru.alex.bank_managersystem.util.exception.handler;

import ru.alex.bank_managersystem.util.exception.ErrorResponse;

import java.time.ZonedDateTime;

public class MoneyAccountNotFoundExceptionHandler implements ExceptionHandlerStrategy {
    @Override
    public ErrorResponse handleException(RuntimeException exception) {
        return ErrorResponse.builder()
                .error("404")
                .msg(exception.getMessage())
                .dateTime(ZonedDateTime.now())
                .build();
    }
}
