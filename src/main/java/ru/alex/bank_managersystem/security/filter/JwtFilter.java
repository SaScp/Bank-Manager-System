package ru.alex.bank_managersystem.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.alex.bank_managersystem.service.JwtService;

import java.io.IOException;



public class JwtFilter extends OncePerRequestFilter {

    @Qualifier("defaultJwtService")
    private final JwtService jwtService;

    private final SecurityContextHolderStrategy securityContextHolderStrategy =
            SecurityContextHolder.getContextHolderStrategy();
    private final AuthenticationEntryPoint authenticationEntryPoint;

    private final SecurityContextRepository securityContextRepository =
            new RequestAttributeSecurityContextRepository();


    private final AuthenticationManager authenticationManager;

    public JwtFilter(JwtService jwtService, AuthenticationEntryPoint authenticationEntryPoint, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final var bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            final var token = bearerToken.substring(7);

            if (!token.isEmpty() && jwtService.validRefreshToken(token)) {
                    try {
                        final var authentication = jwtService.getAuthentication(token);
                        try {
                            final var authResult = authenticationManager.authenticate(authentication);
                            final var context = this.securityContextHolderStrategy.createEmptyContext();

                            context.setAuthentication(authResult);

                            this.securityContextHolderStrategy.setContext(context);
                            this.securityContextRepository.saveContext(context, request, response);

                        } catch (AuthenticationException authenticationException) {
                            this.securityContextHolderStrategy.clearContext();
                            this.authenticationEntryPoint.commence(request, response, authenticationException);
                            return;
                        }
                    } catch (AuthenticationException e) {
                        this.authenticationEntryPoint.commence(request, response, e);
                        return;
                    }
            }
        }
        filterChain.doFilter(request, response);
    }
}
