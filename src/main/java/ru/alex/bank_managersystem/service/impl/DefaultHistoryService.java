package ru.alex.bank_managersystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.alex.bank_managersystem.model.bank_data.Account;
import ru.alex.bank_managersystem.model.bank_data.History;
import ru.alex.bank_managersystem.model.bank_data.TransactionType;
import ru.alex.bank_managersystem.repository.HistoryRepository;
import ru.alex.bank_managersystem.service.HistoryService;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
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

    @Override
    public List<History> getAllHistory(HashMap<String, String> params) {
        if (Boolean.parseBoolean(params.get("np"))) {
            return getAllHistoryNonParams();
        }

        final boolean sortIsOk = Boolean.parseBoolean(params.get("sorted"));
        final int size = Integer.parseInt(params.get("size"));
        final int pageNumber = Integer.parseInt(params.get("pageNumber"));
        final var pageRequest = sortIsOk ? PageRequest.of(pageNumber, size, Sort.by("username")) : PageRequest.of(pageNumber, size);
        return historyRepository.findAll(pageRequest).toList();
    }

    @Override
    public List<History> getAllHistoryNonParams() {
        return historyRepository.findAll();
    }
}
