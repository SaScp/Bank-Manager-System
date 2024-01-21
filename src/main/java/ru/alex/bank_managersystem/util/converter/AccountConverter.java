package ru.alex.bank_managersystem.util.converter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.alex.bank_managersystem.model.bank_data.Account;
import ru.alex.bank_managersystem.model.dto.account.AccountDTO;

@Component
@RequiredArgsConstructor
public class AccountConverter {

    private final ModelMapper modelMapper;

    public AccountDTO convertAccountToAccountDTO(Account account) {
        return modelMapper.map(account, AccountDTO.class);
    }
}
