package com.fpmislata.banco_back.domain.repository;

import com.fpmislata.banco_back.domain.repository.entity.ClientEntity;

import java.util.Optional;

public interface AuthRepository
{
    Optional<ClientEntity> findByUsername(String username);
    Optional<ClientEntity> findByDni(String id);
    ClientEntity register(ClientEntity client);
    boolean existsByUsername(String username);
}
