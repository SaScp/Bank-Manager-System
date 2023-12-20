package ru.alex.bank_managersystem.util.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.alex.bank_managersystem.model.bank_data.CreditHistory;
import ru.alex.bank_managersystem.model.dto.credit.CreditHistoryDTO;

@Component
@RequiredArgsConstructor
public class CreditHistoryConverter {

    private final ModelMapper modelMapper;

    public  CreditHistoryDTO convertCreditHistoryToCreditHistoryDto(CreditHistory creditHistory) {
        return modelMapper.map(creditHistory, CreditHistoryDTO.class);
    }
}
