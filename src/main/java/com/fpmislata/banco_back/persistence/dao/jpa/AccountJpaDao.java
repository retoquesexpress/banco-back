package com.fpmislata.banco_back.persistence.dao.jpa;


import com.fpmislata.banco_back.persistence.dao.jpa.entity.AccountJpaEntity;

import java.util.List;
import java.util.Optional;

public interface AccountJpaDao {

    List<AccountJpaEntity> findByClient();
    AccountJpaEntity getByIban(String iban);
    Optional<AccountJpaEntity> findByIban(String iban);
}
