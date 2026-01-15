package com.fpmislata.banco_back.persistence.dao.jpa.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Account")
public class AccountJpaEntity {
    @Id
    @Column(name = "iban")
    private String iban;
    @Column(name = "balance")
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
    private ClientJpaEntity client;


    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<CreditCardJpaEntity> creditCards;

    public AccountJpaEntity() {
    }

    public AccountJpaEntity(String iban, Double balance, ClientJpaEntity client,
            java.util.List<CreditCardJpaEntity> creditCards) {
        this.iban = iban;
        this.balance = balance;
        this.client = client;
        this.creditCards = creditCards;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public ClientJpaEntity getClient() {
        return client;
    }

    public void setClient(ClientJpaEntity client) {
        this.client = client;
    }


    public List<CreditCardJpaEntity> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCardJpaEntity> creditCards) {
        this.creditCards = creditCards;
    }
}
