package ru.alex.bank_managersystem.util.converter.database;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.stereotype.Component;
import ru.alex.bank_managersystem.model.bank_data.CreditStatus;

@Component
@Converter(autoApply = true)
public class CreditStatusConverter implements AttributeConverter<CreditStatus, String> {
    @Override
    public String convertToDatabaseColumn(CreditStatus attribute) {
        return attribute != null? attribute.name() : null;
    }

    @Override
    public CreditStatus convertToEntityAttribute(String dbData) {
        return dbData != null ? CreditStatus.valueOf(dbData) : null;
    }
}
