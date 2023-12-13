package ru.alex.bank_managersystem.service;


import ru.alex.bank_managersystem.model.dto.user.auth.RegistrationUserDTO;
import ru.alex.bank_managersystem.model.response.JwtResponse;

public interface AuthenticationService {

    JwtResponse registration(RegistrationUserDTO userDTO);

    JwtResponse login(RegistrationUserDTO userDTO);

    JwtResponse refresh(String refreshToken);
}
