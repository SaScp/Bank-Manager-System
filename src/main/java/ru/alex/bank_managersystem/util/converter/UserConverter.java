package ru.alex.bank_managersystem.util.converter;

import org.springframework.stereotype.Component;
import ru.alex.bank_managersystem.model.bank_data.User;
import ru.alex.bank_managersystem.model.dto.user.UserDTO;
import ru.alex.bank_managersystem.model.dto.user.auth.RegistrationUserDTO;

@Component
public class UserConverter {

    public static UserDTO convertUserToUserDTO(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
                .fullName(user.getFullName())
                .build();
    }

    public static User convertRegistrationUserDtoToUser(RegistrationUserDTO userDTO) {
        User user = new User();

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setFullName(userDTO.getFullName());
        user.setDateOfBirth(userDTO.getDateOfBirth());

        return user;
    }


}
