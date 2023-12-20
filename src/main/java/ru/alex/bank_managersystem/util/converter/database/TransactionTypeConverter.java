package ru.alex.bank_managersystem.util.converter.database;

import jakarta.persistence.AttributeConverter;
import ru.alex.bank_managersystem.model.bank_data.Role;
import ru.alex.bank_managersystem.model.bank_data.TransactionType;

public class TransactionTypeConverter implements AttributeConverter<TransactionType, String> {
    @Override
    public String convertToDatabaseColumn(TransactionType attribute) {
        return attribute == null? null : String.valueOf(attribute.name());
    }

    @Override
    public TransactionType convertToEntityAttribute(String dbData) {
        return dbData == null? null : TransactionType.valueOf(dbData);
    }
}
