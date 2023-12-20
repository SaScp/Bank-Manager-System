package ru.alex.bank_managersystem.service;

import ru.alex.bank_managersystem.model.bank_data.Account;
import ru.alex.bank_managersystem.model.bank_data.History;

public interface HistoryService {

    void save(History history, Account account);
}
