package ru.alex.bank_managersystem.util.exception;

public class MoneyAccountNotFoundException extends RuntimeException {
    public MoneyAccountNotFoundException(String msg) {
        super(msg);
    }
}
