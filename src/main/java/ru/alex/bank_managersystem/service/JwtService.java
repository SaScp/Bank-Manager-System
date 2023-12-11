package ru.alex.bank_managersystem.service;

import org.springframework.security.core.Authentication;
import ru.alex.bank_managersystem.model.jwt.AccessJwtObject;
import ru.alex.bank_managersystem.model.jwt.RefreshJwtObject;

public interface JwtService {

    String createJwtAccessToken(AccessJwtObject accessJwtObject);
    String createJwtRefreshToken(RefreshJwtObject refreshJwtObject);
    boolean validRefreshToken(String refreshToken);
    Authentication getAuthentication(String token);

}
