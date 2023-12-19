package ru.alex.bank_managersystem.util.converter.database;

import jakarta.persistence.AttributeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.alex.bank_managersystem.model.bank_data.AccountType;

@Component
@RequiredArgsConstructor
public class AccountTypeConverter implements AttributeConverter<AccountType, String> {


    @Override
    public String convertToDatabaseColumn(AccountType attribute) {
        return attribute == null? null : attribute.name();
    }

    @Override
    public AccountType convertToEntityAttribute(String dbData) {
        return dbData == null? null : AccountType.valueOf(dbData);
    }
}
