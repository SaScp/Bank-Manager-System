package ru.alex.bank_managersystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import ru.alex.bank_managersystem.model.bank_data.*;
import ru.alex.bank_managersystem.model.dto.TransferDTO;
import ru.alex.bank_managersystem.repository.AccountRepository;
import ru.alex.bank_managersystem.repository.UserRepository;
import ru.alex.bank_managersystem.service.AccountService;
import ru.alex.bank_managersystem.service.CardService;
import ru.alex.bank_managersystem.service.HistoryService;
import ru.alex.bank_managersystem.service.UserService;
import ru.alex.bank_managersystem.util.exception.*;
import ru.alex.bank_managersystem.util.validator.CardValidator;

import javax.security.auth.login.AccountNotFoundException;
import java.security.Principal;
import java.time.ZonedDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultAccountService implements AccountService {

    private final AccountRepository accountRepository;

    @Qualifier("defaultCardService")
    private final CardService cardService;

    private final UserRepository userRepository;

    @Qualifier("defaultHistoryService")
    private final HistoryService historyService;

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
        final var accountFrom = getAccountByNumberCard(transferDTO.getFrom());
        final var accountTo = getAccountByNumberCard(transferDTO.getTo());

        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new UserNotFoundException("user not found"));

        if (!accountFrom.getUser().equals(user)) {
            throw new MoneyAccountNotFoundException("account not found");
        }
        double result = accountFrom.getBalance() - transferDTO.getMoney();

        if (result < 0) {
            throw new InsufficientMoneysException("Insufficient Moneys");
        } else {
            accountTo.setBalance(accountTo.getBalance() + transferDTO.getMoney());
            accountFrom.setBalance(result);
        }

        accountRepository.save(accountTo);
        accountRepository.save(accountFrom);

        setHistory(accountFrom, result);
        setHistory(accountTo, result);
    }

    public Account getAccountByNumberCard(String number) {
        return accountRepository.findAccountByCard_CardNumber(number)
                .orElseThrow(() -> new MoneyAccountNotFoundException("account not found"));
    }

    private void setHistory(Account account, double amount) {
        History history = new History();
        history.setAmount(amount);
        // history.setDescription(STR."transfer of money in the amount of \{amount}");
        historyService.save(history, account);
    }

    @Override
    @Transactional
    public Card addCard(String accountId) {
        return cardService.save(new Card(), getAccountById(accountId));
    }

    @Override
    public List<History> getHistory(String id) {
        return accountRepository.findById(id).orElseThrow(() -> new MoneyAccountNotFoundException("Account not found"))
                .getHistories();
    }

    @Override
    public List<Account> getAllAccount(HashMap<String, String> params) {
        if (Boolean.parseBoolean(params.get("np"))) {
            return getAllAccountNonParams();
        }

        final boolean sortIsOk = Boolean.parseBoolean(params.get("sorted"));
        final int size = Integer.parseInt(params.get("size"));
        final int pageNumber = Integer.parseInt(params.get("pageNumber"));
        final var pageRequest = sortIsOk ? PageRequest.of(pageNumber, size, Sort.by("username"))
                : PageRequest.of(pageNumber, size);

        return accountRepository.findAll(pageRequest).toList();
    }

    @Override
    public List<Account> getAllAccountNonParams() {
        return accountRepository.findAll();
    }

}
