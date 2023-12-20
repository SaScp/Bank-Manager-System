package ru.alex.bank_managersystem.service.update;

import ru.alex.bank_managersystem.model.bank_data.User;
import ru.alex.bank_managersystem.model.dto.user.UpdateUserDTO;

public interface UpdateComponent {
    void execute(UpdateUserDTO updateUserDTO, User user);
}
