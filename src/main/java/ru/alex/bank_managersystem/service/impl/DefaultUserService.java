package ru.alex.bank_managersystem.service.impl;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import ru.alex.bank_managersystem.model.bank_data.Role;
import ru.alex.bank_managersystem.model.bank_data.User;
import ru.alex.bank_managersystem.model.dto.user.UserDTO;
import ru.alex.bank_managersystem.repository.UserRepository;
import ru.alex.bank_managersystem.security.authetication.DefaultUserDetails;
import ru.alex.bank_managersystem.service.UserService;
import ru.alex.bank_managersystem.util.converter.UserConverter;
import ru.alex.bank_managersystem.util.exception.SaveUserException;
import ru.alex.bank_managersystem.util.exception.UserNotFoundException;
import ru.alex.bank_managersystem.util.validator.UserValidator;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    @Override
    public User getUserByUUID(final String UUID) {
        return userRepository.findById(UUID).orElseThrow(() -> new UserNotFoundException("User with UUID, not Found"));
    }

    @Transactional
    public User save(User user,  BindingResult bindingResult) {

        var newUUID = UUID.randomUUID().toString();
        if (userRepository.findById(newUUID).isPresent()) {
            newUUID = UUID.randomUUID().toString();
        }
        user.setUserId(newUUID);
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    @Override
    public UserDTO getUserByPrincipal(Principal principal) {
        return UserConverter.convertUserToUserDTO(userRepository
                .findByEmail(principal.getName())
                .orElseThrow(() -> new UserNotFoundException("User with email, not Found")));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
