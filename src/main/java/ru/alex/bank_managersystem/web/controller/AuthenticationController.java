package ru.alex.bank_managersystem.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alex.bank_managersystem.model.dto.user.LoginUserDTO;
import ru.alex.bank_managersystem.model.dto.user.RegistrationUserDTO;
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
    public ResponseEntity<JwtResponse> registrationUser(@RequestBody RegistrationUserDTO registrationUserDTO,
                                                        BindingResult bindingResult) {
        return ResponseEntity.accepted()
                .contentType(MediaType.APPLICATION_JSON)
                .body(authenticationService.registration(registrationUserDTO, bindingResult));
    }
    @PostMapping("/login")
    private ResponseEntity<JwtResponse> loginUser(@RequestBody LoginUserDTO loginUserDTO,
                                                  BindingResult bindingResult) {
        return ResponseEntity.accepted()
                .contentType(MediaType.APPLICATION_JSON)
                .body(authenticationService.login(loginUserDTO, bindingResult));
    }

    @GetMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(@RequestBody String token) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(authenticationService.refresh(token));
    }
}
