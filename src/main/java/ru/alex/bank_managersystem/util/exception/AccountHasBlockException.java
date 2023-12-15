package ru.alex.bank_managersystem.util.exception;

public class AccountHasBlockException extends RuntimeException {
    public AccountHasBlockException(String msg) {
        super(msg);
    }
}
