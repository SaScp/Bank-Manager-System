package ru.alex.bank_managersystem.service;

import ru.alex.bank_managersystem.model.Contact;
import ru.alex.bank_managersystem.model.dto.ContactDTO;

public interface ContactService {

    void save(ContactDTO contact);
}
