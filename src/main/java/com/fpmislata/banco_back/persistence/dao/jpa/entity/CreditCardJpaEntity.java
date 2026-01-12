package com.fpmislata.banco_back.persistence.dao.jpa.entity;

import com.fpmislata.banco_back.domain.service.CreditCardService;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name = "creditCard")
public class CreditCardJpaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCreditCard;
    private String cardNumber;
    private String expirationDate;
    private Integer cvv;
    private String nombreCompleto;

    public  CreditCardJpaEntity() {
    }

    public CreditCardJpaEntity(Integer idCreditCard, String cardNumber, String expirationDate, Integer cvv, String nombreCompleto) {
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

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
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
