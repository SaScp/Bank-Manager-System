package ru.alex.bank_managersystem.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import ru.alex.bank_managersystem.model.bank_data.*;
import ru.alex.bank_managersystem.model.dto.account.AccountDTO;
import ru.alex.bank_managersystem.repository.UserRepository;
import ru.alex.bank_managersystem.service.AccountService;
import ru.alex.bank_managersystem.service.UserService;
import ru.alex.bank_managersystem.util.exception.ResourceNotFoundException;
import ru.alex.bank_managersystem.util.exception.UserNotFoundException;

import java.security.Principal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Qualifier("bCryptPasswordEncoder")
    private final PasswordEncoder passwordEncoder;

    @Qualifier("defaultAccountService")
    private final AccountService accountService;

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
        user.setRole(Role.ROLE_USER);
        user.setDateOfBirth(ZonedDateTime.now());
        user.setAccounts(new ArrayList<>());
        user.setCreditHistories(new ArrayList<>());

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

    @Transactional
    @Override
    public Account addAccount(Principal principal, AccountDTO accountDTO) {
        final var user = getUserByPrincipal(principal);
        final var account = new Account();

        account.setAccountType(chooseType(accountDTO.getAccountType()));

        return accountService.save(account, user);
    }


    private AccountType chooseType(String type) {
        return switch (type) {
            case "DEPOSIT" -> AccountType.DEPOSIT;
            case "CREDIT" -> AccountType.CREDIT;
            case "CALCULATED" -> AccountType.CALCULATED;
            case "CURRENCY" -> AccountType.CURRENCY;
            default -> throw new ResourceNotFoundException(STR."Unexpected value: \{type}");
        };
    }

}
