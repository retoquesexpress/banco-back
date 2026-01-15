package com.fpmislata.banco_back.mapper;

import com.fpmislata.banco_back.controller.webModel.response.ClientDetailResponse;
import com.fpmislata.banco_back.domain.model.Client;
import com.fpmislata.banco_back.domain.repository.entity.ClientEntity;
import com.fpmislata.banco_back.domain.service.dto.ClientDto;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.ClientJpaEntity;

public class ClientMapper {

    private static ClientMapper instance;

    private ClientMapper() {
    }

    public static ClientMapper getInstance() {
        if (instance == null) {
            instance = new ClientMapper();
        }
        return instance;
    }

    public ClientDto fromClientJpaEntityToClientDto(ClientJpaEntity clientJpaEntity) {
        if (clientJpaEntity == null) {
            return null;
        }
        return new ClientDto(
                clientJpaEntity.getDni(),
                clientJpaEntity.getUserName(),
                clientJpaEntity.getPassword(),
                clientJpaEntity.getName(),
                clientJpaEntity.getSurname1(),
                clientJpaEntity.getSurname2(),
                clientJpaEntity.getApiToken());
    }

    public ClientJpaEntity fromClientDtoToClientJpaEntity(ClientDto clientDto) {
        if (clientDto == null) {
            return null;
        }
        return new ClientJpaEntity(
                clientDto.dni(),
                clientDto.userName(),
                clientDto.password(),
                clientDto.name(),
                clientDto.surname1(),
                clientDto.surname2(),
                clientDto.apiToken());
    }

    public ClientDetailResponse fromClientDtoToClientDetailResponse(ClientDto clientDto) {
        if (clientDto == null) {
            return null;
        }
        return new ClientDetailResponse(
                clientDto.dni(),
                clientDto.userName(),
                clientDto.password(),
                clientDto.name(),
                clientDto.surname1(),
                clientDto.surname2(),
                clientDto.apiToken());
    }

    public ClientEntity fromClientJpaEntityToClientEntity(ClientJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new ClientEntity(
                entity.getDni(),
                entity.getUserName(),
                entity.getPassword(),
                entity.getName(),
                entity.getSurname1(),
                entity.getSurname2(),
                entity.getApiToken());
    }

    public ClientDto fromClientEntityToClientDto(ClientEntity clientEntity) {
        if (clientEntity == null) {
            return null;
        }
        return new ClientDto(
                clientEntity.dni(),
                clientEntity.userName(),
                clientEntity.password(),
                clientEntity.name(),
                clientEntity.surname1(),
                clientEntity.surname2(),
                clientEntity.apiToken());
    }

    public Client fromClientDtoToClient(ClientDto clientDto) {
        if (clientDto == null) {
            return null;
        }
        return new Client(
                clientDto.dni(),
                clientDto.userName(),
                clientDto.password(),
                clientDto.name(),
                clientDto.surname1(),
                clientDto.surname2(),
                clientDto.apiToken());
    }

    public ClientDto fromClientToClientDto(Client client) {
        if (client == null) {
            return null;
        }
        return new ClientDto(
                client.getDni(),
                client.getUserName(),
                client.getPassword(),
                client.getName(),
                client.getSurname1(),
                client.getSurname2(),
                client.getApiToken());
    }

    public ClientJpaEntity fromClientEntityToClientJpaEntity(ClientEntity clientEntity) {
        if (clientEntity == null) {
            return null;
        }
        return new ClientJpaEntity(
                clientEntity.dni(),
                clientEntity.userName(),
                clientEntity.password(),
                clientEntity.name(),
                clientEntity.surname1(),
                clientEntity.surname2(),
                clientEntity.apiToken());
    }

    public ClientEntity fromClientToClientEntity(Client client) {
        if (client == null) {
            return null;
        }
        return new ClientEntity(
                client.getDni(),
                client.getUserName(),
                client.getPassword(),
                client.getName(),
                client.getSurname1(),
                client.getSurname2(),
                client.getApiToken());

    }

    public ClientEntity fromClientDtoToClientEntity(ClientDto clientDto) {
        if (clientDto == null) {
            return null;
        }
        return new ClientEntity(
                clientDto.dni(),
                clientDto.userName(),
                clientDto.password(),
                clientDto.name(),
                clientDto.surname1(),
                clientDto.surname2(),
                clientDto.apiToken());
    }
}
