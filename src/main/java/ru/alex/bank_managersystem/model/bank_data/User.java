package ru.alex.bank_managersystem.model.bank_data;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;


@Entity
@Table(name = "t_user")
@Data
public class User implements Serializable {

    public User() {

    }
    public User(String userId,
                String username,
                String password,
                String email,
                String fullName,
                ZonedDateTime dateOfBirth) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
    }

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "date_of_birth")
    private ZonedDateTime dateOfBirth;

    @Column(name = "c_role")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Account> accounts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<CreditHistory> creditHistories;

    public void addAccount(Account account) {
        accounts.add(account);
    }
}
