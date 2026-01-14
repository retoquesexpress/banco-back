package com.fpmislata.banco_back.persistence.dao.jpa;

import com.fpmislata.banco_back.persistence.dao.jpa.entity.AccountJpaEntity;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.ClientJpaEntity;

import java.util.List;
import java.util.Optional;

public interface AccountJpaDao {

    List<AccountJpaEntity> findByClient(ClientJpaEntity client);

    List<AccountJpaEntity> findAll();

    AccountJpaEntity getByIban(String iban);

    Optional<AccountJpaEntity> findByIban(String iban);

}
