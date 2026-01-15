package com.fpmislata.banco_back.domain.repository;

import com.fpmislata.banco_back.domain.model.Client;
import com.fpmislata.banco_back.domain.repository.entity.AccountEntity;
import com.fpmislata.banco_back.domain.repository.entity.CreditCardEntity;
import com.fpmislata.banco_back.domain.service.dto.AccountDto;
import com.fpmislata.banco_back.domain.service.dto.CreditCardDto;

import com.fpmislata.banco_back.domain.repository.entity.ClientEntity;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    List<AccountEntity> findByClient(ClientEntity client);

    List<AccountEntity> findAll();

    AccountEntity getByIban(String iban);

    Optional<AccountEntity> findByIban(String iban);

    Optional<AccountEntity> findAccountByCreditCard(CreditCardEntity creditCardEntity);

    AccountEntity depositMoney(AccountEntity accountEntity, Double amount, String concept, String cardNumber);

    AccountEntity withdrawMoney(AccountEntity accountEntity, Double amount, String concept, String cardNumber);

}
