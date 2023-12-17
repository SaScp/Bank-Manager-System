package ru.alex.bank_managersystem.util.exception;

public class InsufficientMoneysException extends RuntimeException {
    public InsufficientMoneysException(String msg) {
        super(msg);
    }
}
