package ru.alex.bank_managersystem.util.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.alex.bank_managersystem.model.bank_data.Card;
import ru.alex.bank_managersystem.model.dto.account.CardDTO;

@Component
@RequiredArgsConstructor
public class CardConvertor {

    private final ModelMapper modelMapper;

    public  CardDTO convertCardToCardDTO(Card card) {
        return modelMapper.map(card, CardDTO.class);
    }
}
