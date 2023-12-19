package ru.alex.bank_managersystem.util.converter;

import jakarta.persistence.AttributeConverter;
import ru.alex.bank_managersystem.model.bank_data.Account;
import ru.alex.bank_managersystem.model.bank_data.AccountType;
import ru.alex.bank_managersystem.model.dto.AccountDTO;

public class AccountConverter implements AttributeConverter<AccountType, String> {

    public static AccountDTO convertAccountToAccountDTO(Account account) {
        return AccountDTO.builder()
                .accountId(account.getAccountId())
                .accountType(account.getAccountType().name())
                .balance(account.getBalance())
                .dateCreated(account.getDateCreated()).build();
    }

    @Override
    public String convertToDatabaseColumn(AccountType attribute) {
        return attribute == null? null : attribute.name();
    }

    @Override
    public AccountType convertToEntityAttribute(String dbData) {
        return dbData == null? null : AccountType.valueOf(dbData);
    }
}
