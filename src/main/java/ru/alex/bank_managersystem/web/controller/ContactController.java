package ru.alex.bank_managersystem.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alex.bank_managersystem.model.dto.ContactDTO;
import ru.alex.bank_managersystem.service.ContactService;

@RestController
@RequestMapping("/v1/contact")
@RequiredArgsConstructor
public class ContactController {

    @Qualifier("defaultContactService")
    private final ContactService contactService;
    @PostMapping("/")
    public ResponseEntity<HttpStatus> sendContact(@RequestBody ContactDTO contactDTO) {
        contactService.save(contactDTO);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(HttpStatus.CREATED);
    }
}
