package com.fpmislata.banco_back.persistence.dao.jpa.impl;

import com.fpmislata.banco_back.domain.model.Account;
import com.fpmislata.banco_back.domain.service.dto.CreditCardDto;
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
    public List<CreditCardJpaEntity> findAllCreditCardsByAccount(Account account) {
        return entityManager.createQuery(
                        "SELECT c FROM CreditCardJpaEntity c WHERE c.account = :account",
                        CreditCardJpaEntity.class
                )
                .setParameter("account", account)
                .getResultList();
    }

    @Override
    public Optional<CreditCardJpaEntity> findCreditCardById(Integer id) {
        return Optional.ofNullable(entityManager.find(CreditCardJpaEntity.class, id));
    }
}
