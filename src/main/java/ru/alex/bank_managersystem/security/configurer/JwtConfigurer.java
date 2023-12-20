package ru.alex.bank_managersystem.security.configurer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import ru.alex.bank_managersystem.security.authetication.DefaultAuthenticationProvider;
import ru.alex.bank_managersystem.security.filter.JwtFilter;
import ru.alex.bank_managersystem.service.JwtService;

@Component
@RequiredArgsConstructor
public class JwtConfigurer extends AbstractHttpConfigurer<JwtConfigurer, HttpSecurity> {

    @Qualifier("defaultJwtService")
    private final JwtService jwtService;

    @Qualifier("defaultAuthenticationProvider")
    private final AuthenticationProvider authenticationProvider;
    private AuthenticationEntryPoint authenticationEntryPoint =
            (request, response, authException) -> {
                response.addHeader(HttpHeaders.WWW_AUTHENTICATE, "Bearer");
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED");
            };

    @Override
    public void init(HttpSecurity builder) throws Exception {
        builder.exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(this.authenticationEntryPoint));
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {

        builder.addFilterBefore(new JwtFilter(this.jwtService, this.authenticationEntryPoint, authenticationProvider),
                UsernamePasswordAuthenticationFilter.class);
    }

    public JwtConfigurer authenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        return this;
    }
}
