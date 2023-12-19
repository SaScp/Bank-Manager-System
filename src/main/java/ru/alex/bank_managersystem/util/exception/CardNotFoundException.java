package ru.alex.bank_managersystem.util.exception;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(String msg) {
        super(msg);
    }
}
