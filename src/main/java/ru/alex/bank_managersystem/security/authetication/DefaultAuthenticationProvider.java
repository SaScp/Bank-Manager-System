package ru.alex.bank_managersystem.security.authetication;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.alex.bank_managersystem.util.exception.authentication.AccountHasBlockException;

import static java.lang.StringTemplate.STR;

@Component
@RequiredArgsConstructor
public class DefaultAuthenticationProvider implements AuthenticationProvider {

    @Qualifier("defaultUserDetailsService")
    private final UserDetailsService userDetailsService;

    @Qualifier("bCryptPasswordEncoder")
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final var userDetails = userDetailsService.loadUserByUsername(authentication.getName());

        if (!passwordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
            throw new BadCredentialsException("Password error");
        }
        if (userDetails.isAccountNonLocked()) {
            throw new AccountHasBlockException(STR."Account \{authentication.getName()} has block");
        }
        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
