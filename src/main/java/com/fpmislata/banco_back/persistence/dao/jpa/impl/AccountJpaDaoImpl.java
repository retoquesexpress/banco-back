package com.fpmislata.banco_back.persistence.dao.jpa.impl;

import com.fpmislata.banco_back.persistence.dao.jpa.AccountJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.AccountJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class AccountJpaDaoImpl implements AccountJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
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

    @Override
    public AccountJpaEntity create(AccountJpaEntity accountEntity) {
        return entityManager.merge(accountEntity);
    }

    @Override
    public AccountJpaEntity update(AccountJpaEntity accountEntity) {
        return entityManager.merge(accountEntity);
    }

    @Override
    public void deleteById(Integer idAccount) {
        AccountJpaEntity accountJpaEntity = entityManager.find(AccountJpaEntity.class, idAccount);
        if (accountJpaEntity != null) {
            entityManager.remove(accountJpaEntity);
        }
    }
}
