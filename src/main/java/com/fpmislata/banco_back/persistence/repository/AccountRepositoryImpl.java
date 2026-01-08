package com.fpmislata.banco_back.persistence.repository;

import com.fpmislata.banco_back.domain.repository.AccountRepository;
import com.fpmislata.banco_back.domain.repository.entity.AccountEntity;
import com.fpmislata.banco_back.mapper.AccountMapper;
import com.fpmislata.banco_back.persistence.dao.jpa.AccountJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.AccountJpaEntity;

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
    public AccountEntity getByIban(String iban) {
        return accountJpaDao.getByIban(iban)
                .map(AccountMapper.getInstance()::fromAccountJpaEntityToAccountEntity)
                .orElse(null);}

    @Override
    public Optional<AccountEntity> findByIban(String iban) {
        return accountJpaDao.findByIban(iban)
                .map(AccountMapper.getInstance()::fromAccountJpaEntityToAccountEntity);
    }

    @Override
    public AccountEntity create(AccountEntity accountEntity) {
        AccountJpaEntity entity = AccountMapper.getInstance().fromAccountEntityToAccountJpaEntity(accountEntity);
        AccountJpaEntity createdEntity = accountJpaDao.create(entity);
        return AccountMapper.getInstance().fromAccountJpaEntityToAccountEntity(createdEntity);
    }

    @Override
    public AccountEntity update(AccountEntity accountEntity) {
        String iban = accountEntity.iban();
        AccountJpaEntity existingEntity = accountJpaDao.findByIban(iban).orElseThrow(
                () -> new RuntimeException("Cuenta con IBAN " + iban + " no encontrada para actualizar."));
        existingEntity.setBalance(accountEntity.balance());
        AccountJpaEntity updatedEntity = accountJpaDao.update(existingEntity);
        return AccountMapper.getInstance().fromAccountJpaEntityToAccountEntity(updatedEntity);
    }

    @Override
    public void deleteById(Integer idAccount) {
        accountJpaDao.deleteById(idAccount);
    }
}
