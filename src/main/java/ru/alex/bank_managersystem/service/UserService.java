package ru.alex.bank_managersystem.service;

import org.springframework.validation.BindingResult;
import ru.alex.bank_managersystem.model.bank_data.Account;
import ru.alex.bank_managersystem.model.bank_data.User;
import ru.alex.bank_managersystem.model.dto.account.AccountDTO;
import ru.alex.bank_managersystem.model.dto.user.UpdateUserDTO;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

public interface UserService {
    User getUserByUUID(final String UUID);

    User save(User user,  BindingResult bindingResult);

    User getUserByPrincipal(Principal principal);

    User getUserByEmail(String email);

    List<User> getAllUser(HashMap<String, String> params);

    List<User> getAllUserNonParams();

    List<Account> getAccountByPrincipal(Principal principal);

    Account addAccount(Principal principal, AccountDTO accountDTO);

    User userUpdateByPrincipal(UpdateUserDTO userDTO, Principal principal);
}
