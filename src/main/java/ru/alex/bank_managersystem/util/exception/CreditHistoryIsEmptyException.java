package ru.alex.bank_managersystem.util.exception;

public class CreditHistoryIsEmptyException extends RuntimeException {
    public CreditHistoryIsEmptyException(String msg) {
        super(msg);
    }
}
