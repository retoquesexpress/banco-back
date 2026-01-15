package com.fpmislata.banco_back.domain.service.dto;

public record AutorizacionDto(
        String userName,
        String password,
        String apiToken
) {
}
