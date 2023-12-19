package ru.alex.bank_managersystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alex.bank_managersystem.model.bank_data.Card;
import ru.alex.bank_managersystem.model.dto.TransferDTO;
import ru.alex.bank_managersystem.repository.CardRepository;
import ru.alex.bank_managersystem.service.CardService;
import ru.alex.bank_managersystem.util.exception.CardNotFoundException;
import ru.alex.bank_managersystem.util.exception.InsufficientMoneysException;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class DefaultCardService implements CardService {

    private final CardRepository cardRepository;


    Card getCard(String numberCard) {
        return cardRepository.findCardByCardNumber(numberCard).orElseThrow(() -> new CardNotFoundException("Card not found"));
    }
}
