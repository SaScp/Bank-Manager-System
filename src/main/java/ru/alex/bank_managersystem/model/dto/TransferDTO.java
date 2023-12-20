package ru.alex.bank_managersystem.model.dto;

import lombok.Data;
import ru.alex.bank_managersystem.model.bank_data.Card;

@Data
public class TransferDTO {
    private double money;
    private String from;
    private String to;
}
