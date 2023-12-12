package ru.alex.bank_managersystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.bank_managersystem.model.bank_data.Account;
import ru.alex.bank_managersystem.model.bank_data.Card;

@Repository
public interface CardRepository extends JpaRepository<String, Card> {
}
