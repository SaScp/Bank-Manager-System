package ru.alex.bank_managersystem.util.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;
import ru.alex.bank_managersystem.model.bank_data.CreditHistory;
import ru.alex.bank_managersystem.model.bank_data.CreditStatus;
import ru.alex.bank_managersystem.model.dto.user.CreditHistoryDTO;

@Component
@Converter(autoApply = true)
public class CreditHistoryConverter implements AttributeConverter<CreditStatus, String> {

    public static CreditHistoryDTO convertCreditHistoryToCreditHistoryDto(CreditHistory creditHistory) {
        return CreditHistoryDTO.builder()
                .creditAmount(creditHistory.getCreditAmount())
                .creditDate(creditHistory.getCreditDate())
                .interestRate(creditHistory.getInterestRate())
                .repaymentDate(creditHistory.getRepaymentDate())
                .status(creditHistory.getStatus())
                .build();
    }

    @Override
    public String convertToDatabaseColumn(CreditStatus attribute) {
        return attribute != null? attribute.name() : null;
    }

    @Override
    public CreditStatus convertToEntityAttribute(String dbData) {
        return dbData != null ? CreditStatus.valueOf(dbData) : null;
    }
}
