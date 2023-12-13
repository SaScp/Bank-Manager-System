package ru.alex.bank_managersystem.service;

import org.springframework.security.core.Authentication;
import ru.alex.bank_managersystem.model.bank_data.Role;

import ru.alex.bank_managersystem.model.response.JwtResponse;

import java.util.List;
import java.util.Set;

public interface JwtService {

    String createAccessToken(String uuid, String email, Set<Role> role);

    String createRefreshToken(String uuid, String email);

    boolean validatorAccessToken(String token);

    String getUsername(String token);

    boolean validatorRefreshToken(String token);

    String getRefreshUUID(String token);

    JwtResponse refreshUserTokens(String refreshToken);

    String getAccessUUID(String token);

    Authentication getAuthentication(String token);

}
