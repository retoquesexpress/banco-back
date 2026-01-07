package com.fpmislata.banco_back.domain.model;

public class Client {
    private Integer idClient;
    private String name;
    private String apiToken;

    public Client(Integer idClient, String name, String apiToken) {
        this.idClient = idClient;
        this.name = name;
        this.apiToken = apiToken;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }
}
