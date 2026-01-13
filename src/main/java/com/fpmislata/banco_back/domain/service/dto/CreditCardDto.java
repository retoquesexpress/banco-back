package com.fpmislata.banco_back.domain.service.dto;

import jakarta.validation.constraints.NotNull;

public record CreditCardDto(
        @NotNull Integer idCreditCard,
        @NotNull String cardNumber,
        @NotNull String expirationDate,
        @NotNull Integer cvv,
        @NotNull String nombreCompleto

) {
}
