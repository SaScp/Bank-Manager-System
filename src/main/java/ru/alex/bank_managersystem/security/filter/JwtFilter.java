package ru.alex.bank_managersystem.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    @Qualifier("defaultJwtService")
    private final JwtService jwtService;
    private final SecurityContextHolderStrategy securityContextHolderStrategy =
            SecurityContextHolder.getContextHolderStrategy();
    private AuthenticationEntryPoint authenticationEntryPoint;
    private final SecurityContextRepository securityContextRepository =
            new RequestAttributeSecurityContextRepository();
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.substring(7);
            if (!token.isEmpty() && jwtService.validRefreshToken(token)) {
                try {
                    Authentication authentication = jwtService.getAuthentication(token);
                    if (securityContextHolderStrategy.getContext().getAuthentication() == null) {
                        SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
                        context.setAuthentication(authentication);
                        this.securityContextHolderStrategy.setContext(context);
                        this.securityContextRepository.saveContext(context, request, response);
                    }
                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "forbidden");
                    return;
                }
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User unauthorized");
            return;
        }
        filterChain.doFilter(request,response);
    }
}
