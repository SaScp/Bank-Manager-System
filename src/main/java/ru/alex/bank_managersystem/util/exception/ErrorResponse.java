package ru.alex.bank_managersystem.util.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private String error;

    private String msg;

    private ZonedDateTime dateTime;
}
