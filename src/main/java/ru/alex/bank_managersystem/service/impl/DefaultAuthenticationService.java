package ru.alex.bank_managersystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import ru.alex.bank_managersystem.model.bank_data.User;
import ru.alex.bank_managersystem.model.dto.user.auth.RegistrationUserDTO;
import ru.alex.bank_managersystem.model.response.JwtResponse;
import ru.alex.bank_managersystem.service.AuthenticationService;
import ru.alex.bank_managersystem.service.JwtService;
import ru.alex.bank_managersystem.service.UserService;
import ru.alex.bank_managersystem.util.converter.UserConverter;
import ru.alex.bank_managersystem.util.exception.SaveUserException;
import ru.alex.bank_managersystem.util.validator.EmailValidator;
import ru.alex.bank_managersystem.util.validator.PasswordValidator;
import ru.alex.bank_managersystem.util.validator.UserValidator;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DefaultAuthenticationService implements AuthenticationService {

    @Qualifier("defaultUserService")
    private final UserService userService;

    private final UserValidator userValidator;

    @Qualifier("defaultJwtService")
    private final JwtService jwtService;
    @Override
    public JwtResponse registration(RegistrationUserDTO userDTO, BindingResult bindingResult) {
        userValidator.validate(userDTO, bindingResult);


        final var validators = List.of(new EmailValidator(), new PasswordValidator(), userValidator);
        for (var i : validators) {
            if (i.supports(userDTO.getClass()))
                i.validate(userDTO, bindingResult);
        }
        if (bindingResult.hasErrors()) {
            throw new SaveUserException(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        final var user = userService.save(UserConverter.convertRegistrationUserDtoToUser(userDTO), bindingResult);

        final var jwtResponse = new JwtResponse();
        final var id = user.getUserId();
        final var email = user.getEmail();
        final var role = user.getRole();
        jwtResponse.setUuid(id);
        jwtResponse.setUsername(email);

        return JwtResponse.builder()
                .uuid(id)
                .username(email)
                .accessToken(jwtService.createAccessToken(id, email, role))
                .refreshToken(jwtService.createRefreshToken(id, email))
                .build();
    }

    @Override
    public JwtResponse login(RegistrationUserDTO userDTO, BindingResult bindingResult) {
        return null;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return null;
    }
}
