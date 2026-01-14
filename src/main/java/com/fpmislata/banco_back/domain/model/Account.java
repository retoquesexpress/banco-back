package com.fpmislata.banco_back.domain.model;

import java.util.List;

public class Account {
    private String iban;
    private Double balance;
    private Client client;
    private List<CreditCard> creditCards;
    private List<AccountMovement> accountMovements;

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

    public List<AccountMovement> getAccountMovements() {
        return accountMovements;
    }

    public void setAccountMovements(List<AccountMovement> accountMovements) {
        this.accountMovements = accountMovements;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(List<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }
}
