package com.fpmislata.banco_back.persistence.dao.jpa.impl;

import com.fpmislata.banco_back.exception.BusinessException;
import com.fpmislata.banco_back.persistence.dao.jpa.AccountJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.AccountJpaEntity;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.ClientJpaEntity;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.CreditCardJpaEntity;
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

        List<AccountJpaEntity> accounts = entityManager
                .createQuery(
                        "SELECT DISTINCT a FROM AccountJpaEntity a LEFT JOIN FETCH a.creditCards WHERE a.client = :client",
                        AccountJpaEntity.class)
                .setParameter("client", client)
                .getResultList();

        return accounts;
    }

    public List<AccountJpaEntity> findAll() {
        List<AccountJpaEntity> accounts = entityManager
                .createQuery(
                        "SELECT DISTINCT a FROM AccountJpaEntity a LEFT JOIN FETCH a.creditCards",
                        AccountJpaEntity.class)
                .getResultList();

        return accounts;
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
    public Optional<AccountJpaEntity> findAccountByCreditCard(CreditCardJpaEntity creditCardJpaEntity) {
        return entityManager
                .createQuery("SELECT a FROM AccountJpaEntity a JOIN a.creditCards c WHERE c.cardNumber = :cardNumber",
                        AccountJpaEntity.class)
                .setParameter("cardNumber", creditCardJpaEntity.getCardNumber())
                .getResultStream()
                .findFirst();
    }

    @Override
    public AccountJpaEntity save(AccountJpaEntity accountJpaEntity) {
        return entityManager.merge(accountJpaEntity);
    }

    @Override
    public AccountJpaEntity depositMoney(AccountJpaEntity accountJpaEntity, Double amount, String concept) {
        return entityManager.merge(accountJpaEntity);
    }

    @Override
    public AccountJpaEntity withdrawMoney(AccountJpaEntity accountJpaEntity, Double amount, String concept) {
        return entityManager.merge(accountJpaEntity);
    }

}
