package com.fpmislata.banco_back.domain.service.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AccountDto(
        @NotNull String iban,
        Double balance,
        ClientDto client,
        List<AccountMovementDto> movements,
        List<CreditCardDto> creditCards) {
    public AccountDto {
        if (iban == null || iban.isBlank()) {
            throw new IllegalArgumentException("IBAN cannot be null or blank");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
    }
}
