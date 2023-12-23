package ru.alex.bank_managersystem.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.bank_managersystem.model.bank_data.History;
import ru.alex.bank_managersystem.model.dto.account.AccountDTO;
import ru.alex.bank_managersystem.model.dto.account.HistoryDTO;
import ru.alex.bank_managersystem.model.dto.user.UserDTO;
import ru.alex.bank_managersystem.service.AccountService;
import ru.alex.bank_managersystem.service.CardService;
import ru.alex.bank_managersystem.service.HistoryService;
import ru.alex.bank_managersystem.service.UserService;
import ru.alex.bank_managersystem.util.converter.AccountConverter;
import ru.alex.bank_managersystem.util.converter.HistoryConvertor;
import ru.alex.bank_managersystem.util.converter.UserConverter;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    @Qualifier("defaultUserService")
    private final UserService userService;

    @Qualifier("defaultAccountService")
    private final AccountService accountService;

    @Qualifier("defaultHistoryService")
    private final HistoryService historyService;

    private final UserConverter userConverter;

    private final HistoryConvertor historyConvertor;

    private final AccountConverter accountConverter;

    @GetMapping(value = "/all-user", params = {"np"})
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam HashMap<String, String> params) {


        return ResponseEntity
                .ok()
                .body(userService.getAllUser(params).stream()
                        .map(userConverter::convertUserToUserDTO)
                        .toList());
    }

    @GetMapping(value = "/all-history", params = {"np"})
    public ResponseEntity<List<HistoryDTO>> getAllHistory(@RequestParam HashMap<String, String> params) {

        return ResponseEntity
                .ok()
                .body(historyService.getAllHistory(params).stream()
                        .map(historyConvertor::convertHistoryToHistoryDTO)
                        .toList());
    }

    @GetMapping(value = "/all-account", params = {"np"})
    public ResponseEntity<List<AccountDTO>> getAllAccount(@RequestParam HashMap<String, String> params) {

        return ResponseEntity
                .ok()
                .body(accountService.getAllAccount(params).stream()
                        .map(accountConverter::convertAccountToAccountDTO)
                        .toList());
    }


}
