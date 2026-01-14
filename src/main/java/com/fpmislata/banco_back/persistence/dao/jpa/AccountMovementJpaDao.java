package com.fpmislata.banco_back.persistence.dao.jpa;

import com.fpmislata.banco_back.persistence.dao.jpa.entity.AccountMovementJpaEntity;

import java.util.List;
import java.util.Optional;

public interface AccountMovementJpaDao {
    List<AccountMovementJpaEntity> findAllAccountMovements();

    Optional<AccountMovementJpaEntity> getAccountMovementById(Integer idAccountMovement);

    Optional<AccountMovementJpaEntity> findAccountMovementById(Integer idAccountMovement);

    List<AccountMovementJpaEntity> findByCreditCardOrigin(String origin);
}
