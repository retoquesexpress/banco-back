package com.fpmislata.banco_back.domain.service.dto;


import jakarta.validation.constraints.NotNull;

public record ClientDto(
        @NotNull String dni,
        @NotNull String userName,
        @NotNull String password,
        @NotNull String name,
        @NotNull String surname1,
        String surname2,
        @NotNull String apiToken
) {
}
