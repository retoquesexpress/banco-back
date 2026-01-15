package com.fpmislata.banco_back.controller.webModel.request;

import java.time.LocalDate;

public record CreditCardInsertRequest(
                Integer idCreditCard,
                String cardNumber,
                LocalDate expirationDate,
                Integer cvv,
                String nombreCompleto) {
}
