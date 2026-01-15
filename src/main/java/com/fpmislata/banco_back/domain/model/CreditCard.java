package com.fpmislata.banco_back.domain.model;

import java.time.LocalDate;

public class CreditCard {
    private Integer idCreditCard;
    private String cardNumber;
    private LocalDate expirationDate;
    private Integer cvv;
    private String nombreCompleto;

    public CreditCard() {
    }

    public CreditCard(Integer idCreditCard, String cardNumber, LocalDate expirationDate, Integer cvv,
            String nombreCompleto) {
        this.idCreditCard = idCreditCard;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.nombreCompleto = nombreCompleto;
    }

    public Integer getIdCreditCard() {
        return idCreditCard;
    }

    public void setIdCreditCard(Integer idCreditCard) {
        this.idCreditCard = idCreditCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
