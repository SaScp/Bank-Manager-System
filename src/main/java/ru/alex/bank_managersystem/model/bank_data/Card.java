package ru.alex.bank_managersystem.model.bank_data;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Entity
@Table(name = "t_card")
public class Card {

    @Id
    @Column(name = "card_id", nullable = false)
    private String cardId;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "expiration_date", nullable = false)
    private ZonedDateTime expirationDate;

    @Column(name = "cvv", nullable = false)
    private String cvv;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", unique = true)
    private Account account;
}
