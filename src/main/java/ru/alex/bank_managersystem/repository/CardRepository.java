package ru.alex.bank_managersystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.bank_managersystem.model.bank_data.Card;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, String> {
    Optional<Card> findCardByCardNumber(String numberCard);
}
