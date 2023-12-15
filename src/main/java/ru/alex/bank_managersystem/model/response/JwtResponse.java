package ru.alex.bank_managersystem.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String uuid;
    private String username;
    private String accessToken;
    private String refreshToken;
}
