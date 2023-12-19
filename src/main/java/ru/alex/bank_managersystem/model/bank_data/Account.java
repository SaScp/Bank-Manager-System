package ru.alex.bank_managersystem.model.bank_data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "t_account")
public class Account {
    public Account() {}

    @Id
    @Column(name = "account_id")
    private String accountId;


    @Column(name = "balance")
    private Double balance;

    @Column(name = "account_type")
    private AccountType accountType;

    @Column(name = "date_created")
    private ZonedDateTime dateCreated;

    @OneToOne(mappedBy = "account")
    private Card card;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "account")
    private List<History> histories;

}
