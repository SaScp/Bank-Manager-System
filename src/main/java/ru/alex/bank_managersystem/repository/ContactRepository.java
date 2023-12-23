package ru.alex.bank_managersystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.bank_managersystem.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
}
