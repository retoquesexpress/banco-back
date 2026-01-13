package com.fpmislata.banco_back.domain.model;

import java.util.List;

public class Account {
    private String iban;
    private Double balance;
    private Client client;
    private List<CreditCard> creditCards;

    public Account(String iban, Double balance) {
        this.iban = iban;
        this.balance = balance;
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
}
