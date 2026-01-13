package com.fpmislata.banco_back.domain.repository.entity;

import com.fpmislata.banco_back.domain.model.Client;

public record CreditCardEntity(
        Integer idCreditCard,
        String cardNumber,
        String expirationDate,
        Integer cvv,
        String nombreCompleto
) {
    public CreditCardEntity(
            Integer idCreditCard,
            String cardNumber,
            String expirationDate,
            Integer cvv,
            String nombreCompleto
    )
    {
        this.idCreditCard = idCreditCard;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.nombreCompleto = nombreCompleto;
    }
}
