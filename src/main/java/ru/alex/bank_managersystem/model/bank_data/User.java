package ru.alex.bank_managersystem.model.bank_data;


import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Data
public class User {

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

    @OneToMany(mappedBy = "user")
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;

    @OneToMany(mappedBy = "user")
    private List<CreditHistory> creditHistories;

    public User() {

    }
}
