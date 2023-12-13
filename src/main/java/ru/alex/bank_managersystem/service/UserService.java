package ru.alex.bank_managersystem.service;

import org.springframework.security.core.Authentication;
import ru.alex.bank_managersystem.model.bank_data.User;
import ru.alex.bank_managersystem.model.dto.user.UserDTO;
import ru.alex.bank_managersystem.security.authetication.DefaultUserDetails;

import java.security.Principal;

public interface UserService {

    UserDTO getUserByAuthentication(DefaultUserDetails defaultUserDetails);
    User getUserByUUID(final String UUID);

}
