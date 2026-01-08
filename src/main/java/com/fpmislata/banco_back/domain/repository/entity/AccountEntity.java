package com.fpmislata.banco_back.domain.repository.entity;

public record AccountEntity(
    Integer idAccount,
    String iban,
    Double balance
) {}