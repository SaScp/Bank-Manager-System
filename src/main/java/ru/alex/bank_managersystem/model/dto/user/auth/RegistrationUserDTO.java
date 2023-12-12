package ru.alex.bank_managersystem.model.dto.user.auth;

import jakarta.persistence.Column;

import java.time.ZonedDateTime;

public class RegistrationUserDTO {



    private String username;

    private String password;

    private String email;

    private String fullName;

    private ZonedDateTime dateOfBirth;
}
