package ru.alex.bank_managersystem.util.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.alex.bank_managersystem.model.bank_data.History;
import ru.alex.bank_managersystem.model.dto.account.HistoryDTO;

@Component
@RequiredArgsConstructor
public class HistoryConvertor {

    private final ModelMapper modelMapper;

    public HistoryDTO convertHistoryToHistoryDTO(History history) {
        return modelMapper.map(history, HistoryDTO.class);
    }
}
