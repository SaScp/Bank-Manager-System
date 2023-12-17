package ru.alex.bank_managersystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alex.bank_managersystem.model.dto.user.CreditHistoryDTO;

@RestController
@RequestMapping("/v1/account")
public class CreditHistoryController {

    @GetMapping("/{id}")
    public ResponseEntity<CreditHistoryDTO> getCreditHistory(@PathVariable("id") String id) {
        return null;
    }
}
