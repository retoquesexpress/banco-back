package com.fpmislata.banco_back.persistence.dao.jpa;


import com.fpmislata.banco_back.persistence.dao.jpa.entity.AccountJpaEntity;

import java.util.List;
import java.util.Optional;

public interface AccountJpaDao {

    List<AccountJpaEntity> findAll();
    AccountJpaEntity getByIban(String iban);
    Optional<AccountJpaEntity> findByIban(String iban);
    AccountJpaEntity create(AccountJpaEntity accountEntity);
    AccountJpaEntity update(AccountJpaEntity accountEntity);
    void deleteById(Integer idAccount);
}
