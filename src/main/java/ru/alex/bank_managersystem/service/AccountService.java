package ru.alex.bank_managersystem.service;

import org.springframework.validation.BindingResult;
import ru.alex.bank_managersystem.model.bank_data.Account;
import ru.alex.bank_managersystem.model.bank_data.Card;
import ru.alex.bank_managersystem.model.bank_data.History;
import ru.alex.bank_managersystem.model.dto.TransferDTO;

import java.security.Principal;
import java.util.List;

public interface AccountService {
    void transfer(TransferDTO transferDTO, Principal principal);

    Account getAccountById(String id);

    void addCard(Card Card, String accountId, BindingResult bindingResult);

    List<History> getHistory(String id);


}
