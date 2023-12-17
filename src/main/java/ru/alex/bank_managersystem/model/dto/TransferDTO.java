package ru.alex.bank_managersystem.model.dto;

import lombok.Data;

@Data
public class TransferDTO {
    double money;
    String fromAccount;
    String toAccount;
}
