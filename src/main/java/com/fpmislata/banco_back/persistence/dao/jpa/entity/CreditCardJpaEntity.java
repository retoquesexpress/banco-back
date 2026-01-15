package com.fpmislata.banco_back.persistence.dao.jpa.entity;

import com.fpmislata.banco_back.domain.service.CreditCardService;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "creditcard")
public class CreditCardJpaEntity implements Serializable {
    @Id
    @Column(name = "id_credit_card")
    private Integer idCreditCard;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "expiration_date")
    private LocalDate expirationDate;
    @Column(name = "cvv")
    private Integer cvv;
    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_account")
    private AccountJpaEntity account;

    public CreditCardJpaEntity() {
    }

    public CreditCardJpaEntity(Integer idCreditCard, String cardNumber, LocalDate expirationDate, Integer cvv,
            String nombreCompleto, AccountJpaEntity account) {
        this.idCreditCard = idCreditCard;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.nombreCompleto = nombreCompleto;
        this.account = account;
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

    public AccountJpaEntity getAccount() {
        return account;
    }

    public void setAccount(AccountJpaEntity account) {
        this.account = account;
    }
}
