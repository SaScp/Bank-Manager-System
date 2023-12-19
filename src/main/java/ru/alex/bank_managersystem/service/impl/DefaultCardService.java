package ru.alex.bank_managersystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alex.bank_managersystem.model.bank_data.Account;
import ru.alex.bank_managersystem.model.bank_data.Card;
import ru.alex.bank_managersystem.repository.CardRepository;
import ru.alex.bank_managersystem.service.CardService;
import ru.alex.bank_managersystem.util.exception.CardNotFoundException;
import ru.alex.bank_managersystem.util.validator.CardValidator;

import java.time.ZonedDateTime;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultCardService implements CardService {

    private final CardRepository cardRepository;
    private final CardValidator cardValidator;

    public Card getCard(String numberCard) {
        return cardRepository.findCardByCardNumber(numberCard).orElseThrow(() -> new CardNotFoundException("Card not found"));
    }

    public void save(Card card, Account account) {
        card.setAccount(account);
        cardRepository.save(card);
    }

    @Override
    public Card generateCard() {
        Card card = new Card();
        var id = UUID.randomUUID().toString();
        if (cardRepository.findById(id).isPresent()) {
            id = UUID.randomUUID().toString();
        }
        card.setCardId(id);
        card.setCardNumber(generateLuhnNumber());
        card.setCvv(generateCvvCode());
        card.setIsActive(true);
        card.setExpirationDate(ZonedDateTime.now().plusYears(2));

        return card;
    }

    private String generateLuhnNumber() {
        Random random = new Random();
        StringBuilder partialNumber = new StringBuilder();

        for (int i = 0; i < 15; i++) {
            partialNumber.append(random.nextInt(10));
        }

        int sum = 0;
        boolean alternate = false;
        for (int i = partialNumber.length() - 1; i >= 0; i--) {
            int digit = partialNumber.charAt(i) - '0';
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            alternate = !alternate;
        }

        int checksum = (sum * 9) % 10;
        partialNumber.append(checksum);

        return partialNumber.toString();
    }

    private String generateCvvCode() {
        return String.valueOf(new Random().nextInt(100, 1000));
    }
}
