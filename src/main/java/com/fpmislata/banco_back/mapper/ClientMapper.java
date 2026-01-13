package com.fpmislata.banco_back.mapper;

import com.fpmislata.banco_back.controller.webModel.response.ClientDetailResponse;
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
                clientJpaEntity.getApiToken()
        );
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
                clientDto.apiToken()
        );
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
                clientDto.apiToken()
        );
    }





}
