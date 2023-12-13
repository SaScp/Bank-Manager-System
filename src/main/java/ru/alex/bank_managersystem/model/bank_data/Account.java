package ru.alex.bank_managersystem.model.bank_data;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "t_account")
public class Account {
    public Account(String accountId,
                   Double balance,
                   String accountType,
                   ZonedDateTime dateCreated) {
        this.accountId = accountId;
        this.balance = balance;
        this.accountType = accountType;
        this.dateCreated = dateCreated;
    }

    public Account() {}

    @Id
    @Column(name = "account_id")
    private String accountId;


    @Column(name = "balance")
    private Double balance;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "date_created")
    private ZonedDateTime dateCreated;

    @OneToMany(mappedBy = "account")
    private List<Card> cards;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "account")
    private List<History> histories;
}
