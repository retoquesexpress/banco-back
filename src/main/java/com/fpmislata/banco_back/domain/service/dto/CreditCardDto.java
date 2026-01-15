package com.fpmislata.banco_back.domain.service.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreditCardDto(
                @NotNull Integer idCreditCard,
                @NotNull String cardNumber,
                @NotNull LocalDate expirationDate,
                @NotNull Integer cvv,
                @NotNull String nombreCompleto

) {
}
