package ru.alex.bank_managersystem.model.dto.user;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class RegistrationUserDTO {



    private String username;

    private String password;

    private String email;

    private String fullName;

    private ZonedDateTime dateOfBirth;
}
