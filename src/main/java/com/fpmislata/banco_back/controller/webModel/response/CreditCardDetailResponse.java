package com.fpmislata.banco_back.controller.webModel.response;

import jakarta.validation.constraints.NotNull;

public record CreditCardDetailResponse(
        Integer idCreditCard,
        String cardNumber,
        String expirationDate,
        Integer cvv,
        String nombreCompleto

) {
}
