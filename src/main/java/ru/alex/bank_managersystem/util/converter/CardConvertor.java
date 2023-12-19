package ru.alex.bank_managersystem.util.converter;

import ru.alex.bank_managersystem.model.bank_data.Card;
import ru.alex.bank_managersystem.model.dto.account.CardDTO;

public class CardConvertor {

    public static CardDTO convertCardToCardDTO(Card card) {
        return CardDTO.builder()
                .cardNumber(card.getCardNumber())
                .cvv(card.getCvv())
                .expirationDate(card.getExpirationDate())
                .build();
    }
}
