package ru.alex.bank_managersystem.model.dto;

import lombok.Data;

@Data
public class TransferDTO {
    private double money;
    private String fromAccount;
    private String toAccount;
}
