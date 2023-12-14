package ru.alex.bank_managersystem.util.converter;

import org.springframework.stereotype.Component;
import ru.alex.bank_managersystem.model.bank_data.User;
import ru.alex.bank_managersystem.model.dto.user.UserDTO;

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

    public static User convertUserDTOToUser(UserDTO userDTO) {
        return User.builder()
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .dateOfBirth(userDTO.getDateOfBirth())
                .fullName(userDTO.getFullName())
                .build();
    }
    
}
