package ru.alex.bank_managersystem.util.exception;

public class LoginUserException extends RuntimeException {
    public LoginUserException(String msg) {
        super(msg);
    }
}
