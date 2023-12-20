package ru.alex.bank_managersystem.service.update;

import ru.alex.bank_managersystem.model.bank_data.User;
import ru.alex.bank_managersystem.model.dto.user.UpdateUserDTO;

import java.util.Optional;

public class UpdateEmail implements UpdateComponent {
    @Override
    public void execute(UpdateUserDTO updateUserDTO, User user) {
        if (Optional.ofNullable(updateUserDTO.getEmail()).isPresent()) {
            user.setEmail(updateUserDTO.getEmail());
        }
    }
}
