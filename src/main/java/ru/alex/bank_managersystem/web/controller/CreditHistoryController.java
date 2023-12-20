package ru.alex.bank_managersystem.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.bank_managersystem.model.dto.credit.CreditDataDTO;
import ru.alex.bank_managersystem.model.dto.credit.CreditHistoryDTO;
import ru.alex.bank_managersystem.service.CreditHistoryService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/credit-history")
public class CreditHistoryController {

    @Qualifier("defaultCreditHistoryService")
    private final CreditHistoryService creditHistoryService;

    @GetMapping("/{id}")
    public ResponseEntity<CreditHistoryDTO> getCreditHistory(@PathVariable("id") String id) {
        return null;
    }

    @PostMapping("/get-credit")
    public ResponseEntity<HttpStatus> get(@RequestBody CreditDataDTO creditDataDTO, Principal principal) {
            //creditHistoryService.saveCredit();
        return null;
    }


}
