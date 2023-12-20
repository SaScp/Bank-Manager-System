package ru.alex.bank_managersystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import ru.alex.bank_managersystem.model.bank_data.*;
import ru.alex.bank_managersystem.model.dto.TransferDTO;
import ru.alex.bank_managersystem.repository.AccountRepository;
import ru.alex.bank_managersystem.service.AccountService;
import ru.alex.bank_managersystem.service.CardService;
import ru.alex.bank_managersystem.service.UserService;
import ru.alex.bank_managersystem.util.exception.*;
import ru.alex.bank_managersystem.util.validator.CardValidator;

import javax.security.auth.login.AccountNotFoundException;
import java.security.Principal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultAccountService implements AccountService {

    private final AccountRepository accountRepository;

    @Qualifier("defaultCardService")
    private final CardService cardService;


    @Transactional
    public Account save(Account account, User user) {
        var id = UUID.randomUUID().toString();
        if (accountRepository.findById(id).isPresent()) {
            id = UUID.randomUUID().toString();
        }
        account.setAccountId(id);
        account.setUser(user);
        account.setBalance(0.0);
        account.setDateCreated(ZonedDateTime.now());

        return accountRepository.save(account);
    }

    @Override
    public Account getAccountById(String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new MoneyAccountNotFoundException("account not found"));
    }

    @Override
    @Transactional
    public void transfer(TransferDTO transferDTO, Principal principal) {
        final var accountFrom = accountRepository.findAccountByCard_CardNumber(transferDTO.getFrom()).orElseThrow(() ->
                new MoneyAccountNotFoundException("account not found"));
        final var accountTo = accountRepository.findAccountByCard_CardNumber(transferDTO.getTo()).orElseThrow(() ->
                new MoneyAccountNotFoundException("account not found"));;


        double result = accountFrom.getBalance() - transferDTO.getMoney();

        if (result < 0) {
            throw new InsufficientMoneysException("Insufficient Moneys");
        } else {
            accountTo.setBalance(accountTo.getBalance() + transferDTO.getMoney());
            accountFrom.setBalance(result);
        }

        accountRepository.save(accountTo);
        accountRepository.save(accountFrom);
    }

    @Override
    @Transactional
    public Card addCard(String accountId) {
        return cardService.save(new Card(), getAccountById(accountId));
    }

    @Override
    public List<History> getHistory(String id) {
        return accountRepository.findById(id).orElseThrow(() ->
                new MoneyAccountNotFoundException("Account not found")).getHistories();
    }


}
