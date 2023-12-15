package ru.alex.bank_managersystem.util.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alex.bank_managersystem.model.bank_data.User;
import ru.alex.bank_managersystem.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        final var user = (User)target;
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            errors.rejectValue("email", "401", "the user with this email already exists");
        }
    }

}
