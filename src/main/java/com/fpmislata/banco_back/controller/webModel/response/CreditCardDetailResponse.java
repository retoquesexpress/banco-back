package com.fpmislata.banco_back.controller.webModel.response;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreditCardDetailResponse(
                Integer idCreditCard,
                String cardNumber,
                LocalDate expirationDate,
                Integer cvv,
                String nombreCompleto

) {
}
