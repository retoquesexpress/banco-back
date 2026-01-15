package com.fpmislata.banco_back.domain.service.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record TarjetaDto(
        String cardNumber,
        String expirationDate,
        Integer cvv,
        String nombreCompleto
) {
}
