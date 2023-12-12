package ru.alex.bank_managersystem.model.bank_data;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "t_account")
public class Account {

    @Id
    @Column(name = "account_id")
    private String accountId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "date_created")
    private ZonedDateTime dateCreated;

    @OneToMany(mappedBy = "account")
    private List<Card> cards;
}
