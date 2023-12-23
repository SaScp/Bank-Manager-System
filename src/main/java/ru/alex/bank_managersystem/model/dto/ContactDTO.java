package ru.alex.bank_managersystem.model.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ContactDTO {

    private String email;

    private String description;
}
