package ru.alex.bank_managersystem.model.dto.user;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Builder
@Data
public class UserDTO {

    private String username;

    private String email;

    private String fullName;


    private ZonedDateTime dateOfBirth;

}
