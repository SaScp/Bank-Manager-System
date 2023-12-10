package ru.alex.bank_managersystem.util.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alex.bank_managersystem.model.bank_data.Card;

@Component
public class CardValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Card.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Card card = (Card)target;
        if (!isCardCorrect(card.getCard())) {
            errors.rejectValue("card", "500", "the card is not valid");
        }
    }

    private boolean isLuna(final String card) {
        return false; // TODO realize function
    }

    private boolean getDigitCount(final String card) {
        return card.length() == 16;
    }

    private boolean isCardCorrect(final String card) {
        return getDigitCount(card) && isLuna(card);
    }

}
