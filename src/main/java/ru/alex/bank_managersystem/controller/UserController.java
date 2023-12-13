package ru.alex.bank_managersystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alex.bank_managersystem.security.authetication.DefaultUserDetails;
import ru.alex.bank_managersystem.service.UserService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Qualifier("defaultUserService")
    private final UserService userService;

    @GetMapping("/get")
    public String getUser() {

        return null;
    }
}
