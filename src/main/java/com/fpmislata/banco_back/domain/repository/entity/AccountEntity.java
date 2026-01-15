package com.fpmislata.banco_back.domain.repository.entity;

import com.fpmislata.banco_back.exception.BusinessException;
import java.util.List;

public record AccountEntity(
        String iban,
        Double balance,
        ClientEntity client,
        List<AccountMovementEntity> accountMovements,
        List<CreditCardEntity> creditCards
) {

    public AccountEntity depositMoney(Double amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("El importe no puede ser inferior a cero");
        }
        // Devuelve un nuevo record con el balance actualizado
        return new AccountEntity(this.iban, this.balance + amount, this.client, this.accountMovements, this.creditCards);
    }

    public AccountEntity withdrawMoney(Double amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("El importe no puede ser inferior a cero");
        }
        if (this.balance < amount) {
            throw new BusinessException("Saldo insuficiente");
        }
        // Devuelve un nuevo record con el balance actualizado
        return new AccountEntity(this.iban, this.balance - amount, this.client, this.accountMovements, this.creditCards);
    }
}