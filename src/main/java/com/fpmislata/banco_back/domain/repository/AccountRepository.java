package com.fpmislata.banco_back.domain.repository;

import com.fpmislata.banco_back.domain.model.Client;
import com.fpmislata.banco_back.domain.repository.entity.AccountEntity;
import com.fpmislata.banco_back.domain.repository.entity.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    List<AccountEntity> findByClient(ClientEntity client);

    List<AccountEntity> findAll();

    AccountEntity getByIban(String iban);

    Optional<AccountEntity> findByIban(String iban);
}
