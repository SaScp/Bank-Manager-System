package ru.alex.bank_managersystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alex.bank_managersystem.model.dto.user.UserDTO;
import ru.alex.bank_managersystem.model.dto.user.auth.RegistrationUserDTO;
import ru.alex.bank_managersystem.model.response.JwtResponse;
import ru.alex.bank_managersystem.service.AuthenticationService;
import ru.alex.bank_managersystem.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/authentication")
public class AuthenticationController {

    @Qualifier("defaultUserService")
    private final UserService userService;

    @Qualifier("defaultAuthenticationService")
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public ResponseEntity<JwtResponse> registrationUser(@RequestBody RegistrationUserDTO registrationUserDTO) {
        return ResponseEntity.ok().body(authenticationService.registration(registrationUserDTO));
    }
}
