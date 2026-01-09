package com.fpmislata.banco_back.controller.webModel.response;

public record ClientDetailResponse(
        String dni,
        String userName,
        String password,
        String name,
        String surname1,
        String surname2,
        String apiToken

) {
}
