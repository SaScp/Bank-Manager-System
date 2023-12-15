package ru.alex.bank_managersystem.service;

import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import ru.alex.bank_managersystem.model.bank_data.User;
import ru.alex.bank_managersystem.model.dto.user.UserDTO;
import ru.alex.bank_managersystem.security.authetication.DefaultUserDetails;

import java.security.Principal;
import java.util.Optional;

public interface UserService {
    User getUserByUUID(final String UUID);

    void save(User user,  BindingResult bindingResult);

    UserDTO getUserByPrincipal(Principal principal);

    Optional<User> getUserByEmail(String email);
}
