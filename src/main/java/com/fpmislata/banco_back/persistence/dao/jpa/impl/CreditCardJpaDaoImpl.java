package com.fpmislata.banco_back.persistence.dao.jpa.impl;

import com.fpmislata.banco_back.domain.service.dto.AccountDto;
import com.fpmislata.banco_back.persistence.dao.jpa.CreditCardJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.CreditCardJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class CreditCardJpaDaoImpl implements CreditCardJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<CreditCardJpaEntity> findAllCreditCardsByAccount(AccountDto account) {
        return entityManager.createQuery(
                "SELECT c FROM CreditCardJpaEntity c WHERE c.account.iban = :iban",
                CreditCardJpaEntity.class)
                .setParameter("iban", account.iban())
                .getResultList();
    }

    @Override
    public Optional<CreditCardJpaEntity> findCreditCardById(Integer id) {
        return Optional.ofNullable(entityManager.find(CreditCardJpaEntity.class, id));
    }

    @Override
    public Optional<CreditCardJpaEntity> findCreditCardByCardNumber(String cardNumber) {
        return entityManager.createQuery(
                "SELECT c FROM CreditCardJpaEntity c WHERE c.cardNumber = :cardNumber",
                CreditCardJpaEntity.class)
                .setParameter("cardNumber", cardNumber)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<CreditCardJpaEntity> findAll() {
        return entityManager.createQuery("SELECT c FROM CreditCardJpaEntity c", CreditCardJpaEntity.class)
                .getResultList();
    }
}
