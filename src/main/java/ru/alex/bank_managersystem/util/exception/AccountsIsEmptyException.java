package ru.alex.bank_managersystem.util.exception;

public class AccountsIsEmptyException extends RuntimeException {
    public AccountsIsEmptyException(String msg) {
        super(msg);
    }
}
