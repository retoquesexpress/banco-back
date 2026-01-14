package com.fpmislata.banco_back.controller.webModel.request;

import java.util.Date;

public record RegisterRequest(
        String userName,
        String password,
        String email,
        String name,
        String phoneNumber,
        String address,
        Date birthDate,
        String role
) {
}
