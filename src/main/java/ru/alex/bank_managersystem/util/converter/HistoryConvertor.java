package ru.alex.bank_managersystem.util.converter;

import ru.alex.bank_managersystem.model.bank_data.History;
import ru.alex.bank_managersystem.model.dto.HistoryDTO;

public class HistoryConvertor {

    public static HistoryDTO convertHistoryToHistoryDTO(History history) {
        return HistoryDTO.builder()
                .transactionType(history.getTransactionType())
                .transactionDate(history.getTransactionDate())
                .amount(history.getAmount())
                .description(history.getDescription())
                .build();
    }
}
