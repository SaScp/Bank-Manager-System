package ru.alex.bank_managersystem.util.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alex.bank_managersystem.model.bank_data.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TODO realization todo
@Component
public class CardValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Card.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        final var number = (String) target;
        if (!isCardCorrect(number)) {
            errors.rejectValue("cardNumber", "500", "the card is not valid");
        }
    }

    private boolean isLuna(final String card) {
        final var reversCardArray = Arrays.stream(new StringBuilder()
                        .append(card)
                        .reverse()
                        .toString()
                        .split(""))
                .map(Integer::parseInt)
                .toList();
        int sum = 0;
        for (int i = 0; i < reversCardArray.size(); i++) {
            if ((i + 1) % 2 == 0) {
                int t = reversCardArray.get(i) * 2;
                if (t >= 10) {
                    int theWholePart = t / 10;
                    int remains = t % 10;
                    sum += (theWholePart + remains);
                } else {
                    sum += t;
                }
            } else {
                sum += reversCardArray.get(i);
            }
        }

        return (sum % 10) == 0;
    }

    private boolean getDigitCount(final String card) {
        return card.length() == 16;
    }

    private boolean isCardCorrect(final String card) {
        return getDigitCount(card) && isLuna(card);
    }

    public boolean isCardCorrectTest(final String card) {
        return isCardCorrect(card);
    }

    public boolean isLunaTest(final String card) {
        return isLuna(card);
    }
}
