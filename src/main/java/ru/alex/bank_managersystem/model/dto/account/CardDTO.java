package ru.alex.bank_managersystem.model.dto.account;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import ru.alex.bank_managersystem.model.bank_data.Card;

import java.time.ZonedDateTime;

@Data

public class CardDTO {

    private String cardNumber;

    private ZonedDateTime expirationDate;

    private String cvv;

}
