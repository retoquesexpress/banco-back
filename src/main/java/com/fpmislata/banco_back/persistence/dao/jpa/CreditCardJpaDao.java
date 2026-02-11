package com.fpmislata.banco_back.persistence.dao.jpa;

import com.fpmislata.banco_back.domain.model.Account;
import com.fpmislata.banco_back.domain.service.dto.AccountDto;
import com.fpmislata.banco_back.domain.service.dto.CreditCardDto;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.CreditCardJpaEntity;

import java.util.List;
import java.util.Optional;

public interface CreditCardJpaDao {
    List<CreditCardJpaEntity> findAllCreditCardsByAccount(AccountDto account);

    Optional<CreditCardJpaEntity> findCreditCardById(Integer id);

    Optional<CreditCardJpaEntity> findCreditCardByCardNumber(String cardNumber);

    List<CreditCardJpaEntity> findAll();
}
