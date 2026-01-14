package com.fpmislata.banco_back.domain.repository.entity;

import java.util.List;

public record AccountEntity(
        String iban,
        Double balance,
        ClientEntity client,
        List<AccountMovementEntity> accountMovements,
        List<CreditCardEntity> creditCards) {
}