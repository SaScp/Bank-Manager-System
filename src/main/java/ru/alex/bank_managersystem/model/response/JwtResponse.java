package ru.alex.bank_managersystem.model.response;


import lombok.Data;

@Data
public class JwtResponse {
    private String uuid;
    private String username;
    private String accessToken;
    private String refreshToken;
}
