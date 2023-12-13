package ru.alex.bank_managersystem.service.impl.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.alex.bank_managersystem.model.jwt.AccessJwtObject;
import ru.alex.bank_managersystem.model.jwt.RefreshJwtObject;
import ru.alex.bank_managersystem.service.JwtService;

@Service
public class DefaultJwtService implements JwtService {
    @Override
    public String createJwtAccessToken(AccessJwtObject accessJwtObject) {
        return null;
    }

    @Override
    public String createJwtRefreshToken(RefreshJwtObject refreshJwtObject) {
        return null;
    }

    @Override
    public boolean validRefreshToken(String refreshToken) {
        return false;
    }

    @Override
    public Authentication getAuthentication(String token) {
        return null;
    }
}
