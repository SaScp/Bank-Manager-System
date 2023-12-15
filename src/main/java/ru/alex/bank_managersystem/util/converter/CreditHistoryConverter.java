package ru.alex.bank_managersystem.util.converter;

import org.springframework.stereotype.Component;
import ru.alex.bank_managersystem.model.bank_data.CreditHistory;
import ru.alex.bank_managersystem.model.dto.user.CreditHistoryDTO;

@Component
public class CreditHistoryConverter {

    public static CreditHistoryDTO convertCreditHistoryToCreditHistoryDto(CreditHistory creditHistory) {
        return CreditHistoryDTO.builder()
                .creditAmount(creditHistory.getCreditAmount())
                .creditDate(creditHistory.getCreditDate())
                .interestRate(creditHistory.getInterestRate())
                .repaymentDate(creditHistory.getRepaymentDate())
                .status(creditHistory.getStatus())
                .build();
    }
}
