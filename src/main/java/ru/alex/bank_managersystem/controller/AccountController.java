package ru.alex.bank_managersystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alex.bank_managersystem.service.AccountService;

@RestController
@RequestMapping("/v1/account")
@RequiredArgsConstructor
public class AccountController {

    @Qualifier("defaultAccountService")
    private final AccountService accountService;
}
