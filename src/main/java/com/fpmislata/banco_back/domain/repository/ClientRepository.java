package com.fpmislata.banco_back.domain.repository;

import com.fpmislata.banco_back.domain.model.Client;
import com.fpmislata.banco_back.domain.service.dto.ClientDto;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    List<ClientDto> findAllClients();

    Optional<ClientDto> findClientByDni(String dni);

    Optional<ClientDto> getClientByDni(String dni);

    Optional<ClientDto> findClientByUserName(String userName);

    void delete(String dni);

    ClientDto create(ClientDto clientDto);

    ClientDto update(ClientDto clientDto);
}
