package com.fpmislata.banco_back.domain.repository;

import com.fpmislata.banco_back.domain.model.AccountMovement;
import com.fpmislata.banco_back.domain.repository.entity.AccountMovementEntity;

import java.util.List;
import java.util.Optional;

public interface AccountMovementRepository {
    List<AccountMovementEntity> findAllAccountMovements();

    AccountMovementEntity getAccountMovementById(Integer idAccountMovement);

    Optional<AccountMovementEntity> findAccountMovementById(Integer idAccountMovement);

    List<AccountMovementEntity> findByCreditCardOrigin(String origin);

    List<AccountMovementEntity> findAllMovementsByAccount(String iban);
}
