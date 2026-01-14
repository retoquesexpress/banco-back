package com.fpmislata.banco_back.persistence.dao.jpa.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account")
public class AccountJpaEntity {
    @Id
    private String iban;
    @Column
    private Double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
    private ClientJpaEntity client;

    public AccountJpaEntity() {
    }

    public AccountJpaEntity(String iban, Double balance, ClientJpaEntity client) {
        this.iban = iban;
        this.balance = balance;
        this.client = client;
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
}
