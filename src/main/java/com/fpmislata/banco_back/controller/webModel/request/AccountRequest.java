package com.fpmislata.banco_back.controller.webModel.request;

public record AccountRequest(
                String iban,
                Double balance) {
}
