package ru.alex.bank_managersystem.service;

import ru.alex.bank_managersystem.model.bank_data.Account;
import ru.alex.bank_managersystem.model.bank_data.History;

import java.util.HashMap;
import java.util.List;

public interface HistoryService {

    void save(History history, Account account);

    List<History> getAllHistory(HashMap<String, String> params);

    List<History> getAllHistoryNonParams();
}
