package com.fpmislata.banco_back.controller.webModel.request;

public record CreditCardInsertRequest(
        Integer idCreditCard,
        String cardNumber,
        String expirationDate,
        Integer cvv,
        String nombreCompleto
) {
}
