package com.fpmislata.banco_back.controller.webModel.response;

public record AccountResponse(
        String iban,
        Double balance
) {
}
