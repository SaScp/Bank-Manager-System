package ru.alex.bank_managersystem.model.dto.user;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import ru.alex.bank_managersystem.model.bank_data.Account;
import ru.alex.bank_managersystem.model.dto.account.AccountDTO;

import java.time.ZonedDateTime;
import java.util.List;


@Data

public class UserDTO {

    private String username;

    private String email;

    private String fullName;

    private ZonedDateTime dateOfBirth;

    private List<AccountDTO> accounts;
}
