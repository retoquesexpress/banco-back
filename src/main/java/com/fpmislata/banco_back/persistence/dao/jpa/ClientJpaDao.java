package com.fpmislata.banco_back.persistence.dao.jpa;

import com.fpmislata.banco_back.domain.repository.entity.ClientEntity;
import com.fpmislata.banco_back.domain.service.dto.ClientDto;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.ClientJpaEntity;

import java.util.List;
import java.util.Optional;

public interface ClientJpaDao {
    List<ClientJpaEntity> findAllClients();

    Optional<ClientJpaEntity> findClientByDni(String dni);

    Optional<ClientJpaEntity> getClientByDni(String dni);

    Optional<ClientJpaEntity> findClientByUserName(String userName);

    void delete(String dni);

    ClientJpaEntity create(ClientJpaEntity clientJpaEntity);

    ClientJpaEntity update(ClientJpaEntity clientJpaEntity);
}
