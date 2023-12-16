package ru.alex.bank_managersystem.model.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class AccountDTO {

    private Double balance;

    private String accountType;

    private ZonedDateTime dateCreated;
}
