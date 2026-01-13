package com.fpmislata.banco_back.controller.webModel.request;

public record CreditCardUpdateRequest(
        Integer idCreditCard,
        String cardNumber,
        String expirationDate,
        Integer cvv,
        String nombreCompleto

) {
}
