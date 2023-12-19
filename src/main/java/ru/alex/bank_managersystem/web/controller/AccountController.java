package ru.alex.bank_managersystem.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.bank_managersystem.model.dto.account.AccountDTO;
import ru.alex.bank_managersystem.model.dto.account.CardDTO;
import ru.alex.bank_managersystem.model.dto.account.HistoryDTO;
import ru.alex.bank_managersystem.model.dto.TransferDTO;
import ru.alex.bank_managersystem.service.AccountService;
import ru.alex.bank_managersystem.util.converter.AccountConverter;
import ru.alex.bank_managersystem.util.converter.CardConvertor;
import ru.alex.bank_managersystem.util.converter.HistoryConvertor;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/v1/account")
@RequiredArgsConstructor
public class AccountController {

    @Qualifier("defaultAccountService")
    private final AccountService accountService;

    private final CardConvertor cardConvertor;

    private final AccountConverter accountConverter;

    @PostMapping("/transfer")
    public ResponseEntity<HttpStatus> transferMoney(@RequestBody TransferDTO transferDTO, Principal principal) {
        accountService.transfer(transferDTO, principal);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(HttpStatus.ACCEPTED);
    }

    @GetMapping("/history/{id}")
    public ResponseEntity<List<HistoryDTO>> getHistory(@PathVariable("id") String id) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(accountService.getHistory(id)
                        .stream()
                        .map(HistoryConvertor::convertHistoryToHistoryDTO)
                        .toList());
    }

    @PostMapping("/add-card/{id}")
    public ResponseEntity<CardDTO> addCard(@PathVariable("id") String id) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(cardConvertor.convertCardToCardDTO(accountService.addCard(id)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> account(@PathVariable("id") String id) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(accountConverter
                        .convertAccountToAccountDTO(accountService.getAccountById(id)));
    }
}
