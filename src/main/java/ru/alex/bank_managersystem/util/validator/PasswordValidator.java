package ru.alex.bank_managersystem.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alex.bank_managersystem.model.bank_data.User;

public class PasswordValidator implements Validator {

   private final static String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final var user = (User) target;

        if (!user.getPassword().matches(passwordRegex)) {
            errors.rejectValue("password", "401", "password is invalid");
        }
    }
}
