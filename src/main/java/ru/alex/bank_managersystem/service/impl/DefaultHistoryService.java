package ru.alex.bank_managersystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alex.bank_managersystem.model.bank_data.Account;
import ru.alex.bank_managersystem.model.bank_data.History;
import ru.alex.bank_managersystem.model.bank_data.TransactionType;
import ru.alex.bank_managersystem.repository.HistoryRepository;
import ru.alex.bank_managersystem.service.HistoryService;

import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultHistoryService implements HistoryService {


    private final HistoryRepository historyRepository;

    @Override
    public void save(History history, Account account) {
       history.setTransactionId(UUID.randomUUID().toString());
       history.setTransactionType(TransactionType.DEFAULT_TRANSACTION);
       history.setTransactionDate(ZonedDateTime.now());
       history.setAccount(account);
       historyRepository.save(history);
    }
}
