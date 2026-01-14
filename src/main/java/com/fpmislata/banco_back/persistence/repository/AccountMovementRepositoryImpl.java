package com.fpmislata.banco_back.persistence.repository;

import java.util.List;
import java.util.Optional;

import com.fpmislata.banco_back.domain.repository.AccountMovementRepository;
import com.fpmislata.banco_back.domain.repository.entity.AccountMovementEntity;
import com.fpmislata.banco_back.mapper.AccountMovementMapper;
import com.fpmislata.banco_back.persistence.dao.jpa.AccountMovementJpaDao;

public class AccountMovementRepositoryImpl implements AccountMovementRepository {
    private final AccountMovementJpaDao accountMovementJpaDao;

    public AccountMovementRepositoryImpl(AccountMovementJpaDao accountMovementJpaDao) {
        this.accountMovementJpaDao = accountMovementJpaDao;
    }

    @Override
    public List<AccountMovementEntity> findAllAccountMovements() {
        return accountMovementJpaDao.findAllAccountMovements()
                .stream()
                .map(AccountMovementMapper.getInstance()::fromAccountMovementJpaEntityToAccountMovementEntity)
                .toList();
    }

    @Override
    public AccountMovementEntity getAccountMovementById(Integer idAccountMovement) {
        return accountMovementJpaDao.getAccountMovementById(idAccountMovement)
                .map(AccountMovementMapper.getInstance()::fromAccountMovementJpaEntityToAccountMovementEntity)
                .orElse(null);
    }

    @Override
    public Optional<AccountMovementEntity> findAccountMovementById(Integer idAccountMovement) {
        return accountMovementJpaDao.findAccountMovementById(idAccountMovement)
                .map(AccountMovementMapper.getInstance()::fromAccountMovementJpaEntityToAccountMovementEntity);
    }

    @Override
    public List<AccountMovementEntity> findByCreditCardOrigin(String origin) {
        return accountMovementJpaDao.findByCreditCardOrigin(origin)
                .stream()
                .map(AccountMovementMapper.getInstance()::fromAccountMovementJpaEntityToAccountMovementEntity)
                .toList();
    }
}
