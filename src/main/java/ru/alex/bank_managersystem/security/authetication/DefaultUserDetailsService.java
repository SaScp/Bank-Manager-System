package ru.alex.bank_managersystem.security.authetication;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.alex.bank_managersystem.model.bank_data.User;
import ru.alex.bank_managersystem.service.UserService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DefaultUserDetailsService implements UserDetailsService {

    @Qualifier("defaultUserService")
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        final var optionalUser = userService.getUserByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        final var user = optionalUser.get();
        return DefaultUserDetails
                .builder()
                .user(user)
                .authorities(Set.of(new SimpleGrantedAuthority(user.getRole().name())))
                .build();
    }
}
