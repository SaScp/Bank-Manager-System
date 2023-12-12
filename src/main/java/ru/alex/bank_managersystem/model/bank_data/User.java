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

    @OneToMany(mappedBy = "user_id")
    private List<Account> accounts;

    @OneToMany(mappedBy = "user_id")
    private List<CreditHistory> creditHistories;
}
