package ru.alex.bank_managersystem.model.dto.account;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;


@Data
@Builder
public class HistoryDTO {

    private String transactionType;

    private Double amount;

    private ZonedDateTime transactionDate;

    private String description;
}
