package com.fpmislata.banco_back.controller.webModel.request;

public record ClientUpdateRequest(
        String dni,
        String userName,
        String password,
        String name,
        String surname1,
        String surname2,
        String apiToken
) {
}
