package com.fpmislata.banco_back.domain.repository;

import com.fpmislata.banco_back.domain.repository.entity.AccountEntity;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    List<AccountEntity> findAll();
    AccountEntity getByIban(String iban);
    Optional<AccountEntity> findByIban(String iban);
    AccountEntity create(AccountEntity accountEntity);
    AccountEntity update(AccountEntity accountEntity);
    void deleteById(Integer idAccount);
}
