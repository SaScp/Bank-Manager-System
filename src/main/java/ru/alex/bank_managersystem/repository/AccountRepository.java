package ru.alex.bank_managersystem.repository;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.bank_managersystem.model.bank_data.Account;

@Repository
public interface AccountRepository extends JpaRepository<String, Account> {
}
