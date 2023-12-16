package ru.alex.bank_managersystem.util.converter;

import ru.alex.bank_managersystem.model.bank_data.Account;
import ru.alex.bank_managersystem.model.dto.AccountDTO;

public class AccountConverter {

    public static AccountDTO convertAccountToAccountDTO(Account account) {
        return AccountDTO.builder()
                .accountType(account.getAccountType())
                .balance(account.getBalance())
                .dateCreated(account.getDateCreated()).build();
    }
}
