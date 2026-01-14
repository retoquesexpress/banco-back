package com.fpmislata.banco_back.persistence.dao.jpa.impl;

import com.fpmislata.banco_back.persistence.dao.jpa.AccountJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.AccountJpaEntity;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.ClientJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class AccountJpaDaoImpl implements AccountJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AccountJpaEntity> findByClient(ClientJpaEntity client) {
        return entityManager
                .createQuery("SELECT a FROM AccountJpaEntity a WHERE a.client = :client", AccountJpaEntity.class)
                .setParameter("client", client)
                .getResultList();
    }


    public List<AccountJpaEntity> findAll() {
        return entityManager.createQuery("SELECT a FROM AccountJpaEntity a", AccountJpaEntity.class)
                .getResultList();
    }

    @Override
    public AccountJpaEntity getByIban(String iban) {
        return entityManager.find(AccountJpaEntity.class, iban);
    }

    @Override
    public Optional<AccountJpaEntity> findByIban(String iban) {
        return Optional.ofNullable(entityManager.find(AccountJpaEntity.class, iban));
    }

}
