package com.fpmislata.banco_back.persistence.repository;


import com.fpmislata.banco_back.domain.repository.AuthRepository;
import com.fpmislata.banco_back.domain.repository.entity.ClientEntity;
import com.fpmislata.banco_back.mapper.ClientMapper;
import com.fpmislata.banco_back.persistence.dao.jpa.ClientJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.ClientJpaEntity;
import jakarta.transaction.Transactional;

import java.util.Optional;

public class AuthRepositoryImpl implements AuthRepository {

    private final ClientJpaDao clientJpaDao;

    public AuthRepositoryImpl(ClientJpaDao clientJpaDao) {
        this.clientJpaDao = clientJpaDao;
    }


    @Override
    public Optional<ClientEntity> findByUsername(String username) {
        Optional<ClientJpaEntity> clientJpaEntity = clientJpaDao.findClientByUserName(username);
        return clientJpaEntity.map(entity -> ClientMapper.getInstance().fromClientJpaEntityToClientEntity(entity));
    }

    @Override
    public Optional<ClientEntity> findByDni(String dni) {
        Optional<ClientJpaEntity> clientJpaEntity = clientJpaDao.findClientByDni(dni);
        return clientJpaEntity.map(entity -> ClientMapper.getInstance().fromClientJpaEntityToClientEntity(entity));
    }

    @Override
    public ClientEntity register(ClientEntity client) {
        ClientJpaEntity clientJpaEntity = new ClientJpaEntity(
                client.dni(),
                client.userName(),
                client.password(),
                client.name(),
                client.surname1(),
                client.surname2(),
                client.apiToken()
        );
        ClientJpaEntity savedEntity = clientJpaDao.create(clientJpaEntity);
        return ClientMapper.getInstance().fromClientJpaEntityToClientEntity(savedEntity);
    }

    @Override
    public boolean existsByUsername(String username) {
        return clientJpaDao.findClientByUserName(username).isPresent();
    }
}
