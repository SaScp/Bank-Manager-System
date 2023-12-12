package ru.alex.bank_managersystem.service;

import org.springframework.security.core.Authentication;
import ru.alex.bank_managersystem.model.dto.user.UserDTO;

public interface UserService {

    UserDTO getUserByAuthentication(Authentication authentication);


}
