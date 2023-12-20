package ru.alex.bank_managersystem.model.dto.credit;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data

public class CreditHistoryDTO {

    private Double creditAmount;

    private Double interestRate;

    private ZonedDateTime creditDate;

    private ZonedDateTime repaymentDate;

    private String status;
}
