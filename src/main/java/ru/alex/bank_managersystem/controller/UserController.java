package ru.alex.bank_managersystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alex.bank_managersystem.model.dto.user.CreditHistoryDTO;
import ru.alex.bank_managersystem.model.dto.user.UserDTO;
import ru.alex.bank_managersystem.service.UserService;
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
    public UserDTO getUser(Principal principal) {
        return UserConverter.convertUserToUserDTO(userService.getUserByPrincipal(principal));
    }

    @GetMapping("/credit-history")
    public List<CreditHistoryDTO> getCreditHistory(Principal principal) {
        return userService.getCreditHistoryByPrincipal(principal)
                .stream()
                .map(CreditHistoryConverter::convertCreditHistoryToCreditHistoryDto)
                .toList();
    }
}
