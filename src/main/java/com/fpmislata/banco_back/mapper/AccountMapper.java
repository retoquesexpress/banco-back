package com.fpmislata.banco_back.mapper;

import com.fpmislata.banco_back.domain.repository.entity.AccountEntity;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.AccountJpaEntity;

public class AccountMapper {
    private static AccountMapper INSTANCE;
    private AccountMapper() {
    }

    public static AccountMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountMapper();
        }
        return INSTANCE;
    }

    public Object fromAccountJpaEntityToAccountEntity(AccountJpaEntity accountJpaEntity) {
        if (accountJpaEntity == null) {
            return null;
        }
        return new AccountEntity(
                accountJpaEntity.getIban(),
                accountJpaEntity.getBalance());
    }

    public AccountJpaEntity fromAccountEntityToAccountJpaEntity(AccountEntity accountEntity) {
        if (accountEntity == null) {
            return null;
        }
        return new AccountJpaEntity(
                accountEntity.iban(),
                accountEntity.balance());
    }
}
