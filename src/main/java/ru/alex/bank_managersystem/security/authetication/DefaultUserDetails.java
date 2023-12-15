package ru.alex.bank_managersystem.security.authetication;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.alex.bank_managersystem.model.bank_data.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public class DefaultUserDetails implements UserDetails {

    private User user;
    Collection<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        List<String> auth = authorities.stream().map(SimpleGrantedAuthority::getAuthority).toList();
        return auth.contains("ROLE_BLOCK");
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
