package ru.alex.bank_managersystem.model.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {
    private String uuid;
    private String username;
    private String accessToken;
    private String refreshToken;
}
