package ru.alex.bank_managersystem.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import ru.alex.bank_managersystem.model.bank_data.*;
import ru.alex.bank_managersystem.model.dto.AccountDTO;
import ru.alex.bank_managersystem.model.dto.user.UserDTO;
import ru.alex.bank_managersystem.repository.AccountRepository;
import ru.alex.bank_managersystem.repository.UserRepository;
import ru.alex.bank_managersystem.service.UserService;
import ru.alex.bank_managersystem.util.converter.UserConverter;
import ru.alex.bank_managersystem.util.exception.AccountsIsEmptyExceotion;
import ru.alex.bank_managersystem.util.exception.CreditHistoryIsEmptyException;
import ru.alex.bank_managersystem.util.exception.ResourceNotFoundException;
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

    private final AccountRepository accountRepository;

    @Override
    public User getUserByUUID(final String UUID) {
        return userRepository.findById(UUID).orElseThrow(() -> new UserNotFoundException("User with UUID, not Found"));
    }

    @Transactional
    public User save(User user, BindingResult bindingResult) {

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
        return getUserByEmail(principal.getName());
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public List<CreditHistory> getCreditHistoryByPrincipal(Principal principal) {
        return getUserByPrincipal(principal)
                .getCreditHistories();

    }

    @Override
    public List<Account> getAccountByPrincipal(Principal principal) {
        return getUserByPrincipal(principal).getAccounts();
    }

    @Override
    public boolean addAccount(Principal principal, AccountDTO accountDTO) {
        final var user = getUserByPrincipal(principal);
        final var account = new Account();

        var id = UUID.randomUUID().toString();
        if (accountRepository.findById(id).isPresent()) {
            id = UUID.randomUUID().toString();
        }
        account.setAccountId(id);
        account.setUser(user);
        account.setBalance(0.0);
        account.setCards(new ArrayList<>());
        account.setDateCreated(ZonedDateTime.now());
        account.setAccountType(chooseType(accountDTO.getAccountType()));

        accountRepository.save(account);
        user.addAccount(account);

        userRepository.save(user);
        return true;
    }
    public  AccountType chooseType(String type) {
        return switch (type) {
            case "DEPOSIT" ->  AccountType.DEPOSIT;
            case "CREDIT" ->  AccountType.CREDIT;
            case "CALCULATED" ->  AccountType.CALCULATED;
            case "CURRENCY" ->  AccountType.CURRENCY;
            default -> throw new ResourceNotFoundException(STR."Unexpected value: \{type}");
        };
    }

}
