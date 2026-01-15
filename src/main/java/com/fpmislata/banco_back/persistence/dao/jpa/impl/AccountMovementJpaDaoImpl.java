package com.fpmislata.banco_back.persistence.dao.jpa.impl;

import com.fpmislata.banco_back.persistence.dao.jpa.AccountMovementJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.AccountMovementJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class AccountMovementJpaDaoImpl implements AccountMovementJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<AccountMovementJpaEntity> findAllAccountMovements() {
        return entityManager.createQuery("SELECT am FROM AccountMovementJpaEntity am").getResultList();
    }

    @Override
    public Optional<AccountMovementJpaEntity> getAccountMovementById(Integer idAccountMovement) {
        return Optional.ofNullable(entityManager.find(AccountMovementJpaEntity.class, idAccountMovement));
    }

    @Override
    public Optional<AccountMovementJpaEntity> findAccountMovementById(Integer idAccountMovement) {
        return Optional.ofNullable(entityManager.find(AccountMovementJpaEntity.class, idAccountMovement));
    }

    @Override
    public List<AccountMovementJpaEntity> findByCreditCardOrigin(String origin) {
        return entityManager
                .createQuery("SELECT am FROM AccountMovementJpaEntity am WHERE am.creditCardOrigin = :origin",
                        AccountMovementJpaEntity.class)
                .setParameter("origin", origin)
                .getResultList();
    }

    @Override
    public List<AccountMovementJpaEntity> findAllMovementsByAccount(String iban) {
        return entityManager
                .createQuery(
                        "SELECT am FROM AccountMovementJpaEntity am JOIN CreditCardJpaEntity cc ON am.creditCardOrigin = cc.cardNumber WHERE cc.account.iban = :iban",
                        AccountMovementJpaEntity.class)
                .setParameter("iban", iban)
                .getResultList();
    }
}
