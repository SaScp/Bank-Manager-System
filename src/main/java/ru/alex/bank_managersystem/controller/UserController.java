package ru.alex.bank_managersystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alex.bank_managersystem.model.dto.AccountDTO;
import ru.alex.bank_managersystem.model.dto.user.CreditHistoryDTO;
import ru.alex.bank_managersystem.model.dto.user.UserDTO;
import ru.alex.bank_managersystem.service.UserService;
import ru.alex.bank_managersystem.util.converter.AccountConverter;
import ru.alex.bank_managersystem.util.converter.CreditHistoryConverter;
import ru.alex.bank_managersystem.util.converter.UserConverter;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    @Qualifier("defaultUserService")
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<UserDTO> getUser(Principal principal) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(UserConverter
                        .convertUserToUserDTO(userService.getUserByPrincipal(principal))
                );
    }

    @GetMapping("/credit-history")
    public ResponseEntity<List<CreditHistoryDTO>> getCreditHistory(Principal principal) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.getCreditHistoryByPrincipal(principal).stream()
                        .map(CreditHistoryConverter::convertCreditHistoryToCreditHistoryDto)
                        .toList()
                );
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDTO>> getAccount(Principal principal) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.getAccountByPrincipal(principal).stream()
                        .map(AccountConverter::convertAccountToAccountDTO).toList());
    }
}
