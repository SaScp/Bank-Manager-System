package ru.alex.bank_managersystem.model.dto.user;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserDTO {

    private String email;

    private String password;
}
