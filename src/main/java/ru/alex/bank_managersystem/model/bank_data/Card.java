package ru.alex.bank_managersystem.model.bank_data;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "t_card")
public class Card {

    @Id
    @Column(name = "card_id")
    private String cardId;

    @Column(name = "card_number")
    private String cardNumber;


    @Column(name = "expiration_date")
    private ZonedDateTime expirationDate;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", unique = true)
    private Account account;
}
