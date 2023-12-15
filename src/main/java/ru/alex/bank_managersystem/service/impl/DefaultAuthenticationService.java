package ru.alex.bank_managersystem.service.impl;

import org.springframework.stereotype.Service;
import ru.alex.bank_managersystem.model.dto.user.auth.RegistrationUserDTO;
import ru.alex.bank_managersystem.model.response.JwtResponse;
import ru.alex.bank_managersystem.service.AuthenticationService;

@Service
public class DefaultAuthenticationService implements AuthenticationService {
    @Override
    public JwtResponse registration(RegistrationUserDTO userDTO) {
        return null;
    }

    @Override
    public JwtResponse login(RegistrationUserDTO userDTO) {
        return null;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return null;
    }
}
