package ru.alex.bank_managersystem.util.exception.handler;

import ru.alex.bank_managersystem.util.exception.ErrorResponse;

public interface ExceptionHandlerStrategy {

    ErrorResponse handleException(RuntimeException exception);
}
