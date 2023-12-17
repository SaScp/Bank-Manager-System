package ru.alex.bank_managersystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.alex.bank_managersystem.model.bank_data.Account;
import ru.alex.bank_managersystem.model.bank_data.Card;
import ru.alex.bank_managersystem.model.bank_data.History;
import ru.alex.bank_managersystem.model.dto.TransferDTO;
import ru.alex.bank_managersystem.repository.AccountRepository;
import ru.alex.bank_managersystem.service.AccountService;
import ru.alex.bank_managersystem.service.UserService;
import ru.alex.bank_managersystem.util.exception.AccessDeniedException;
import ru.alex.bank_managersystem.util.exception.CardValidatorException;
import ru.alex.bank_managersystem.util.exception.InsufficientMoneysException;
import ru.alex.bank_managersystem.util.exception.MoneyAccountNotFoundException;
import ru.alex.bank_managersystem.util.validator.CardValidator;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultAccountService implements AccountService {

    private final AccountRepository accountRepository;
    private final CardValidator cardValidator;

    @Override
    public Account getAccountById(String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new MoneyAccountNotFoundException("account not found"));
    }
    @Override
    public void transfer(TransferDTO transferDTO, Principal principal) {
        //TODO переделить как нибудь
      /*  final var fromAccount = getAccountById(principal.getName());
        final var toAccount = getAccountById(transferDTO.getToAccount());
        double result = transferDTO.getMoney() - fromAccount.getBalance();
        if (result >= 0.0) {
            fromAccount.setBalance(result);
            toAccount.setBalance(transferDTO.getMoney() + toAccount.getBalance());
        } else {
            throw new InsufficientMoneysException("insufficient money");
        }*/

    }
    @Override
    public void addCard(Card card, String accountId, BindingResult bindingResult) {

        if (cardValidator.supports(card.getClass())) {
            final var account = getAccountById(accountId);
            cardValidator.validate(account, bindingResult);
            if (bindingResult.hasErrors()) {
                throw new CardValidatorException(bindingResult.getFieldError().getDefaultMessage());
            }
            account.addCard(card);
        } else {
            throw new CardValidatorException("Card validator invalid");
        }
    }

    @Override
    public List<History> getHistory(String id) {
        return accountRepository.findById(id).orElseThrow(() -> new MoneyAccountNotFoundException("Account not found")).getHistories();
    }




}
