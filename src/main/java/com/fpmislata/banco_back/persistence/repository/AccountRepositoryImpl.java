package com.fpmislata.banco_back.persistence.repository;

import com.fpmislata.banco_back.domain.repository.AccountRepository;
import com.fpmislata.banco_back.domain.repository.entity.AccountEntity;
import com.fpmislata.banco_back.domain.repository.entity.AccountMovementEntity;
import com.fpmislata.banco_back.domain.repository.entity.ClientEntity;
import com.fpmislata.banco_back.mapper.AccountMapper;
import com.fpmislata.banco_back.mapper.AccountMovementMapper;
import com.fpmislata.banco_back.mapper.ClientMapper;
import com.fpmislata.banco_back.persistence.dao.jpa.AccountJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.AccountMovementJpaDao;

import java.util.List;
import java.util.Optional;

public class AccountRepositoryImpl implements AccountRepository {
    private final AccountJpaDao accountJpaDao;
    private final AccountMovementJpaDao accountMovementJpaDao;

    public AccountRepositoryImpl(AccountJpaDao accountJpaDao, AccountMovementJpaDao accountMovementJpaDao) {
        this.accountJpaDao = accountJpaDao;
        this.accountMovementJpaDao = accountMovementJpaDao;
    }

    @Override
    public List<AccountEntity> findAll() {
        return accountJpaDao.findAll()
                .stream()
                .map(accountJpaEntity -> {
                    AccountEntity account = AccountMapper.getInstance()
                            .fromAccountJpaEntityToAccountEntity(accountJpaEntity);
                    // Fetch movements for all credit cards of this account
                    List<AccountMovementEntity> movements = accountJpaEntity.getCreditCards().stream()
                            .flatMap(creditCard -> accountMovementJpaDao
                                    .findByCreditCardOrigin(creditCard.getCardNumber()).stream())
                            .map(AccountMovementMapper
                                    .getInstance()::fromAccountMovementJpaEntityToAccountMovementEntity)
                            .toList();
                    // Create new AccountEntity with movements
                    return new AccountEntity(account.iban(), account.balance(), account.client(), movements,
                            account.creditCards());
                })
                .toList();
    }

    @Override
    public List<AccountEntity> findByClient(ClientEntity client) {
        return accountJpaDao
                .findByClient(ClientMapper.getInstance().fromClientEntityToClientJpaEntity(client))
                .stream()
                .map(accountJpaEntity -> {
                    AccountEntity account = AccountMapper.getInstance()
                            .fromAccountJpaEntityToAccountEntity(accountJpaEntity);
                    // Fetch movements for all credit cards of this account
                    List<AccountMovementEntity> movements = accountJpaEntity.getCreditCards().stream()
                            .flatMap(creditCard -> accountMovementJpaDao
                                    .findByCreditCardOrigin(creditCard.getCardNumber()).stream())
                            .map(AccountMovementMapper
                                    .getInstance()::fromAccountMovementJpaEntityToAccountMovementEntity)
                            .toList();
                    // Create new AccountEntity with movements
                    return new AccountEntity(account.iban(), account.balance(), account.client(), movements,
                            account.creditCards());
                })
                .toList();
    }

    @Override
    public AccountEntity getByIban(String iban) {
        return accountJpaDao.findByIban(iban)
                .map(accountJpaEntity -> {
                    AccountEntity account = AccountMapper.getInstance()
                            .fromAccountJpaEntityToAccountEntity(accountJpaEntity);
                    // Fetch movements for all credit cards of this account
                    List<AccountMovementEntity> movements = accountJpaEntity.getCreditCards().stream()
                            .flatMap(creditCard -> accountMovementJpaDao
                                    .findByCreditCardOrigin(creditCard.getCardNumber()).stream())
                            .map(AccountMovementMapper
                                    .getInstance()::fromAccountMovementJpaEntityToAccountMovementEntity)
                            .toList();
                    // Create new AccountEntity with movements
                    return new AccountEntity(account.iban(), account.balance(), account.client(), movements,
                            account.creditCards());
                })
                .orElse(null);
    }

    @Override
    public Optional<AccountEntity> findByIban(String iban) {
        return accountJpaDao.findByIban(iban)
                .map(accountJpaEntity -> {
                    AccountEntity account = AccountMapper.getInstance()
                            .fromAccountJpaEntityToAccountEntity(accountJpaEntity);
                    // Fetch movements for all credit cards of this account
                    List<AccountMovementEntity> movements = accountJpaEntity.getCreditCards().stream()
                            .flatMap(creditCard -> accountMovementJpaDao
                                    .findByCreditCardOrigin(creditCard.getCardNumber()).stream())
                            .map(AccountMovementMapper
                                    .getInstance()::fromAccountMovementJpaEntityToAccountMovementEntity)
                            .toList();
                    // Create new AccountEntity with movements
                    return new AccountEntity(account.iban(), account.balance(), account.client(), movements,
                            account.creditCards());
                });
    }

}
