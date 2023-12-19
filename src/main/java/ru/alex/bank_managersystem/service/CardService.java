package ru.alex.bank_managersystem.service;

import ru.alex.bank_managersystem.model.bank_data.Account;
import ru.alex.bank_managersystem.model.bank_data.Card;

public interface CardService {


    Card getCard(String numberCard);

    Card generateCard();

    void save(Card card, Account account);
}
