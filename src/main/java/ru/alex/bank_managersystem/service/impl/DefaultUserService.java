package ru.alex.bank_managersystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.alex.bank_managersystem.model.bank_data.User;
import ru.alex.bank_managersystem.model.dto.user.UserDTO;
import ru.alex.bank_managersystem.repository.UserRepository;
import ru.alex.bank_managersystem.security.authetication.DefaultUserDetails;
import ru.alex.bank_managersystem.service.AccountService;
import ru.alex.bank_managersystem.service.UserService;
import ru.alex.bank_managersystem.util.converter.UserConverter;
import ru.alex.bank_managersystem.util.exception.UserNotFoundException;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO getUserByAuthentication(DefaultUserDetails defaultUserDetails) {
        return UserConverter.convertUserToUserDTO(userRepository
                .findByEmail(defaultUserDetails.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User with email, not Found")));
    }

    @Override
    public User getUserByUUID(final String UUID) {
        return userRepository.findById(UUID).orElseThrow(() -> new UserNotFoundException("User with UUID, not Found"));
    }

}
