package ru.alex.bank_managersystem.model.bank_data;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_role")
public class Role {

    @Id
    private String role_id;

    @Column(name = "role_name")
    private String roleName;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
}
