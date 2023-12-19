package ru.alex.bank_managersystem.util.converter;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.alex.bank_managersystem.model.bank_data.User;
import ru.alex.bank_managersystem.model.dto.user.UserDTO;
import ru.alex.bank_managersystem.model.dto.user.auth.RegistrationUserDTO;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final ModelMapper modelMapper;
    public UserDTO convertUserToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public  User convertRegistrationUserDtoToUser(RegistrationUserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }


}
