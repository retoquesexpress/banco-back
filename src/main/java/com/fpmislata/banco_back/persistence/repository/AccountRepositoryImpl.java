package com.fpmislata.banco_back.persistence.repository;

import com.fpmislata.banco_back.domain.repository.AccountRepository;
import com.fpmislata.banco_back.domain.repository.entity.AccountEntity;
import com.fpmislata.banco_back.domain.repository.entity.ClientEntity;
import com.fpmislata.banco_back.mapper.AccountMapper;
import com.fpmislata.banco_back.mapper.ClientMapper;
import com.fpmislata.banco_back.persistence.dao.jpa.AccountJpaDao;

import java.util.List;
import java.util.Optional;

public class AccountRepositoryImpl implements AccountRepository {
    private final AccountJpaDao accountJpaDao;

    public AccountRepositoryImpl(AccountJpaDao accountJpaDao) {
        this.accountJpaDao = accountJpaDao;
    }

    @Override
    public List<AccountEntity> findAll() {
        return accountJpaDao.findAll()
                .stream()
                .map(AccountMapper.getInstance()::fromAccountJpaEntityToAccountEntity)
                .toList();
    }

    @Override
    public List<AccountEntity> findByClient(ClientEntity client) {
        return accountJpaDao
                .findByClient(ClientMapper.getInstance().fromClientEntityToClientJpaEntity(client))
                .stream()
                .map(AccountMapper.getInstance()::fromAccountJpaEntityToAccountEntity)
                .toList();
    }

    @Override
    public AccountEntity getByIban(String iban) {
        return accountJpaDao.findByIban(iban)
                .map(AccountMapper.getInstance()::fromAccountJpaEntityToAccountEntity)
                .orElse(null);
    }

    @Override
    public Optional<AccountEntity> findByIban(String iban) {
        return accountJpaDao.findByIban(iban)
                .map(AccountMapper.getInstance()::fromAccountJpaEntityToAccountEntity);
    }

}
