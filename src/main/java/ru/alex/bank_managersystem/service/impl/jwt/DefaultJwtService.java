package ru.alex.bank_managersystem.service.impl.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.alex.bank_managersystem.model.bank_data.Role;
import ru.alex.bank_managersystem.model.bank_data.User;

import ru.alex.bank_managersystem.model.response.JwtResponse;
import ru.alex.bank_managersystem.security.authetication.DefaultUserDetails;
import ru.alex.bank_managersystem.security.authetication.DefaultUserDetailsService;
import ru.alex.bank_managersystem.service.JwtService;
import ru.alex.bank_managersystem.service.UserService;
import ru.alex.bank_managersystem.util.exception.AccessDeniedException;

import java.sql.Date;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultJwtService implements JwtService {

    @Qualifier("defaultUserService")
    private final UserService userService;

    @Qualifier("defaultUserDetailsService")
    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret.access}")
    private String accessSecret;

    @Value("${jwt.secret.refresh}")
    private String refreshSecret;

    private static final ZonedDateTime now = ZonedDateTime.now();

    @Override
    public String createAccessToken(String uuid,
                                    String email,
                                    Role role) {

        return JWT.create()
                .withSubject("user")
                .withClaim("id", uuid)
                .withClaim("email", email)
                .withClaim("roles", role.name())
                .withIssuedAt(Instant.from(now))
                .withIssuer("Server")
                .withExpiresAt(Instant.from(now.plusMinutes(90)))
                .sign(Algorithm.HMAC256(accessSecret));
    }

    @Override
    public String createRefreshToken(String uuid,
                                     String email) {

        return JWT.create()
                .withSubject("user")
                .withClaim("id", uuid)
                .withClaim("email", email)
                .withIssuedAt(Instant.from(now))
                .withIssuer("Server")
                .withExpiresAt(Instant.from(now.plusDays(60)))
                .sign(Algorithm.HMAC256(refreshSecret));
    }

    @Override
    public JwtResponse refreshUserTokens(String refreshToken) {
        JwtResponse jwtResponse = new JwtResponse();

        if (!validatorRefreshToken(refreshToken)) {
            throw new AccessDeniedException("refresh token a is invalid");
        }

        String id = getRefreshUUID(refreshToken);
        User user = userService.getUserByUUID(id);

        jwtResponse.setUuid(id);
        jwtResponse.setUsername(user.getEmail());
        jwtResponse.setAccessToken(createAccessToken(id, user.getEmail(), user.getRole()));
        jwtResponse.setRefreshToken(createRefreshToken(id, user.getEmail()));

        return jwtResponse;
    }

    @Override
    public boolean validatorAccessToken(String token) {
        DecodedJWT decodedJWT = getVerifier(token, accessSecret);
        if (decodedJWT == null) {
            return false;
        }
        return !Date.from(now.toInstant()).equals(getVerifier(token, accessSecret).getExpiresAt());
    }

    @Override
    public boolean validatorRefreshToken(String token) {
        DecodedJWT decodedJWT = getVerifier(token, refreshSecret);
        if (decodedJWT == null) {
            return false;
        }
        return !Date.from(now.toInstant()).equals(getVerifier(token, refreshSecret).getExpiresAt());
    }

    @Override
    public String getRefreshUUID(String token) {
        return getVerifier(token, refreshSecret).getClaim("id").asString();
    }

    @Override
    public String getAccessUUID(String token) {
        return getVerifier(token, accessSecret).getClaim("id").asString();
    }

    @Override
    public String getUsername(String token) {
        return getVerifier(token, accessSecret).getClaim("email").asString();
    }

    @Override
    public Authentication getAuthentication(String token) {
        String email = getUsername(token);

        DefaultUserDetails userDetails = (DefaultUserDetails) userDetailsService.loadUserByUsername(email);

        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), "", userDetails.getAuthorities());
    }

    private DecodedJWT getVerifier(String token, String secret) {
        DecodedJWT decodedJWT;
        try {
            decodedJWT = JWT.require(Algorithm.HMAC256(secret))
                    .withSubject("user")
                    .withIssuer("Server")
                    .build()
                    .verify(token);
        } catch (Exception e) {
            return null;
        }
        return decodedJWT;
    }
    private String getRole(Role role) {
        return role.name();
    }
}
