package ru.alex.bank_managersystem.service;

import org.springframework.validation.BindingResult;
import ru.alex.bank_managersystem.model.bank_data.Account;
import ru.alex.bank_managersystem.model.bank_data.CreditHistory;
import ru.alex.bank_managersystem.model.bank_data.User;
import ru.alex.bank_managersystem.model.dto.AccountDTO;
import ru.alex.bank_managersystem.model.dto.user.UserDTO;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User getUserByUUID(final String UUID);

    User save(User user,  BindingResult bindingResult);

    User getUserByPrincipal(Principal principal);

    User getUserByEmail(String email);

    List<CreditHistory> getCreditHistoryByPrincipal(Principal principal);

    List<Account> getAccountByPrincipal(Principal principal);

    Account addAccount(Principal principal, AccountDTO accountDTO);
}
