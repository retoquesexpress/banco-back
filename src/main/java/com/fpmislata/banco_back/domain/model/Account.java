package com.fpmislata.banco_back.domain.model;

import java.util.List;

public class Account {
    private Integer idAccount;
    private String accountNumber;
    private Double balance;
    private Client client;
    private List<CreditCard> creditCards;

    public Account(Integer idAccount, String accountNumber, Double balance) {
        this.idAccount = idAccount;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
