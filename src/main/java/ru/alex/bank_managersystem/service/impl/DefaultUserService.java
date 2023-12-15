package ru.alex.bank_managersystem.service.impl;



import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import ru.alex.bank_managersystem.model.bank_data.CreditHistory;
import ru.alex.bank_managersystem.model.bank_data.Role;
import ru.alex.bank_managersystem.model.bank_data.User;
import ru.alex.bank_managersystem.model.dto.user.UserDTO;
import ru.alex.bank_managersystem.repository.UserRepository;
import ru.alex.bank_managersystem.service.UserService;
import ru.alex.bank_managersystem.util.converter.UserConverter;
import ru.alex.bank_managersystem.util.exception.CreditHistoryIsEmptyException;
import ru.alex.bank_managersystem.util.exception.UserNotFoundException;

import java.security.Principal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Qualifier("bCryptPasswordEncoder")
    private final PasswordEncoder passwordEncoder;
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

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserId(newUUID);
        user.setRole(Role.USER);
        user.setDateOfBirth(ZonedDateTime.now());

        return userRepository.save(user);
    }

    @Override
    public User getUserByPrincipal(Principal principal) {
        return userRepository
                .findByEmail(principal.getName())
                .orElseThrow(() -> new UserNotFoundException("User with email, not Found"));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<CreditHistory> getCreditHistoryByPrincipal(Principal principal) {
        final var creditHistories = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UserNotFoundException("User not found"))
                .getCreditHistories();
        if (creditHistories.isEmpty()) {
            throw new CreditHistoryIsEmptyException("Credits not founds");
        } else {
            return creditHistories;
        }
    }


}
