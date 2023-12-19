package ru.alex.bank_managersystem.model.dto.account;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import ru.alex.bank_managersystem.model.bank_data.AccountType;

import java.time.ZonedDateTime;

@Data
@Builder
public class AccountDTO {

    private String accountId;

    private Double balance;

    private String accountType;

    private ZonedDateTime dateCreated;

    private CardDTO card;
}
