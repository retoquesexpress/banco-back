package com.fpmislata.banco_back.domain.repository.entity;

public record AccountEntity(
    String iban,
    Double balance
) {}