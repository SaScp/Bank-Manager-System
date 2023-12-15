package ru.alex.bank_managersystem.service;


import org.springframework.validation.BindingResult;
import ru.alex.bank_managersystem.model.dto.user.auth.RegistrationUserDTO;
import ru.alex.bank_managersystem.model.response.JwtResponse;

public interface AuthenticationService {

    JwtResponse registration(RegistrationUserDTO userDTO, BindingResult bindingResult);

    JwtResponse login(RegistrationUserDTO userDTO, BindingResult bindingResult);

    JwtResponse refresh(String refreshToken);
}
