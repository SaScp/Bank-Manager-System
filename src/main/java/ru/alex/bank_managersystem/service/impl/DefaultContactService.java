package ru.alex.bank_managersystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.alex.bank_managersystem.model.Contact;
import ru.alex.bank_managersystem.model.dto.ContactDTO;
import ru.alex.bank_managersystem.repository.ContactRepository;
import ru.alex.bank_managersystem.service.ContactService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultContactService implements ContactService {


    private final ContactRepository contactRepository;

    @Override
    public void save(ContactDTO contactDTO) {
        Contact contact = new Contact();
        contact.setContactId(UUID.randomUUID().toString());
        contact.setDescription(contactDTO.getDescription());
        contact.setEmail(contactDTO.getEmail());
        contactRepository.save(contact);
    }


}
