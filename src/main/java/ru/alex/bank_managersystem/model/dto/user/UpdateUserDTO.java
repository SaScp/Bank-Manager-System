package ru.alex.bank_managersystem.model.dto.user;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class UpdateUserDTO {
    private String username;

    private String email;

    private String fullName;

}
