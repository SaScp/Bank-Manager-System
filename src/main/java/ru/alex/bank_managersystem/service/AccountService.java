package ru.alex.bank_managersystem.service;

import org.springframework.validation.BindingResult;
import ru.alex.bank_managersystem.model.bank_data.Account;
import ru.alex.bank_managersystem.model.bank_data.Card;
import ru.alex.bank_managersystem.model.bank_data.History;
import ru.alex.bank_managersystem.model.bank_data.User;
import ru.alex.bank_managersystem.model.dto.TransferDTO;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

public interface AccountService {
    void transfer(TransferDTO transferDTO, Principal principal);

    Account getAccountById(String id);

    Card addCard(String accountId);

    List<History> getHistory(String id);

    List<Account> getAllAccount(HashMap<String, String> params);
    Account getAccountByNumberCard(String number);
    List<Account> getAllAccountNonParams();
    Account save(Account account, User user);

}
