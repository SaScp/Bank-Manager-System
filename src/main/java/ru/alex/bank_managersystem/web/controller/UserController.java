package ru.alex.bank_managersystem.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.bank_managersystem.model.dto.account.AccountDTO;
import ru.alex.bank_managersystem.model.dto.user.UpdateUserDTO;
import ru.alex.bank_managersystem.model.dto.user.UserDTO;
import ru.alex.bank_managersystem.service.UserService;
import ru.alex.bank_managersystem.util.converter.AccountConverter;
import ru.alex.bank_managersystem.util.converter.UserConverter;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    @Qualifier("defaultUserService")
    private final UserService userService;

    private final UserConverter userConverter;

    private final AccountConverter accountConverter;

    @GetMapping("/")
    public ResponseEntity<UserDTO> getUser(Principal principal) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userConverter
                        .convertUserToUserDTO(userService.getUserByPrincipal(principal))
                );
    }


    @GetMapping("/accounts")
    public ResponseEntity<List<AccountDTO>> getAccount(Principal principal) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userService.getAccountByPrincipal(principal).stream()
                        .map(accountConverter::convertAccountToAccountDTO).toList());
    }

    @PostMapping("/add")
    public ResponseEntity<AccountDTO> addAccount(Principal principal,
                                                 @RequestBody AccountDTO accountDTO) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(accountConverter.convertAccountToAccountDTO(userService.addAccount(principal, accountDTO)));
    }

    @PatchMapping("/update-user")
    public ResponseEntity<UserDTO> update(@RequestBody UpdateUserDTO updateUserDTO, Principal principal) {
        userService.userUpdateByPrincipal(updateUserDTO, principal);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(userConverter
                        .convertUserToUserDTO(userService
                                .userUpdateByPrincipal(updateUserDTO, principal))
                );
    }
}
