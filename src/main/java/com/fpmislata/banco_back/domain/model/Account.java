package com.fpmislata.banco_back.domain.model;

import com.fpmislata.banco_back.exception.BusinessException;

import java.math.BigDecimal;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }




    public void depositMoney(Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("El importe no puede ser inferior a cero");
        }
        this.balance = this.balance += amount;
    }

    public void withdrawMoney(Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("El importe no puede ser inferior a cero");
        }
        if (this.balance < amount) {
            throw new BusinessException("Saldo insuficiente");
        }
        this.balance = this.balance -= amount;
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
