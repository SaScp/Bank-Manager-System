package ru.alex.bank_managersystem.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PasswordValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
