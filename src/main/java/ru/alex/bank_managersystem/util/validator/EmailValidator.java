package ru.alex.bank_managersystem.util.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alex.bank_managersystem.model.bank_data.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements Validator {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        final var user = (User) target;
        if (isValidEmail(user.getEmail())) {
            errors.rejectValue("email", "500", "email is invalid");
        }
    }


    private boolean isValidEmail(String email) {
        if (email == null) {
            return true;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return !matcher.matches();
    }
}
