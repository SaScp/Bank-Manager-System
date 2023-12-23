package ru.alex.bank_managersystem.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "t_contact")
public class Contact {

    @Id
    private String contactId;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;
}
