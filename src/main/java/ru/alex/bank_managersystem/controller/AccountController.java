package ru.alex.bank_managersystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alex.bank_managersystem.model.dto.TransferDTO;
import ru.alex.bank_managersystem.service.AccountService;

import java.security.Principal;

@RestController
@RequestMapping("/v1/account")
@RequiredArgsConstructor
public class AccountController {

    @Qualifier("defaultAccountService")
    private final AccountService accountService;

    @PostMapping("/transfer")
    public ResponseEntity<HttpStatus> transferMoney(@RequestBody TransferDTO transferDTO, Principal principal) {

        accountService.transfer(transferDTO, principal);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(HttpStatus.ACCEPTED);
    }
}
