package ru.alex.bank_managersystem.util.converter.database;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alex.bank_managersystem.model.bank_data.Role;

import java.time.LocalDateTime;

@Converter(autoApply = true)
public class RoleConvertor implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role attribute) {
        return attribute == null? null : String.valueOf(attribute.name());
    }

    @Override
    public Role convertToEntityAttribute(String dbData) {
       return dbData == null? null : Role.valueOf(dbData);
    }


}
