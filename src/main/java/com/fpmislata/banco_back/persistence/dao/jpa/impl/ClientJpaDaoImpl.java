package com.fpmislata.banco_back.persistence.dao.jpa.impl;

import com.fpmislata.banco_back.persistence.dao.jpa.ClientJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.ClientJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class ClientJpaDaoImpl implements ClientJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ClientJpaEntity> findAllClients() {
        return entityManager.createQuery("SELECT c FROM ClientJpaEntity c", ClientJpaEntity.class)
                .getResultList();
    }

    @Override
    public Optional<ClientJpaEntity> findClientByDni(String dni) {
        return Optional.ofNullable(entityManager.find(ClientJpaEntity.class, dni));
    }

    @Override
    public Optional<ClientJpaEntity> getClientByDni(String dni) {
        return Optional.ofNullable(entityManager.find(ClientJpaEntity.class, dni));
    }

    @Override
    public Optional<ClientJpaEntity> findClientByUserName(String userName) {
        return entityManager
                .createQuery("SELECT c FROM ClientJpaEntity c WHERE c.userName = :userName", ClientJpaEntity.class)
                .setParameter("userName", userName)
                .getResultStream()
                .findFirst();
    }

    @Override
    public void delete(String dni) {
        ClientJpaEntity clientJpaEntity = entityManager.find(ClientJpaEntity.class, dni);
        if (clientJpaEntity != null) {
            entityManager.remove(clientJpaEntity);
        }
    }

    @Override
    public ClientJpaEntity create(ClientJpaEntity clientJpaEntity) {
        return entityManager.merge(clientJpaEntity);
    }

    @Override
    public ClientJpaEntity update(ClientJpaEntity clientJpaEntity) {
        return entityManager.merge(clientJpaEntity);
    }
}
