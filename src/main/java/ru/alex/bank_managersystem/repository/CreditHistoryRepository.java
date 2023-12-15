package ru.alex.bank_managersystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.bank_managersystem.model.bank_data.CreditHistory;
import ru.alex.bank_managersystem.model.bank_data.User;

import java.util.Optional;

@Repository
public interface CreditHistoryRepository extends JpaRepository<CreditHistory, String> {


}
