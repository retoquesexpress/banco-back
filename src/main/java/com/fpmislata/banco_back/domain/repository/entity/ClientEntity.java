package com.fpmislata.banco_back.domain.repository.entity;

public record ClientEntity(
        String dni,
        String userName,
        String password,
        String name,
        String surname1,
        String surname2,
        String apiToken

) {
    public ClientEntity(
            String dni,
            String userName,
            String password,
            String name,
            String surname1,
            String surname2,
            String apiToken
    )
    {
        this.dni = dni;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.apiToken = apiToken;
    }
}