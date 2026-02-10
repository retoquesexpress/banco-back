package com.fpmislata.banco_back.domain.repository;

import com.fpmislata.banco_back.domain.model.Account;
import com.fpmislata.banco_back.domain.service.dto.AccountDto;
import com.fpmislata.banco_back.domain.service.dto.ClientDto;
import com.fpmislata.banco_back.domain.service.dto.CreditCardDto;

import java.util.List;
import java.util.Optional;

public interface CreditCardRepository {
    List<CreditCardDto> findAllCreditCardsByAccount(AccountDto account);

    Optional<CreditCardDto> findCreditCardById(Integer id);

    Optional<CreditCardDto> findCreditCardByCardNumber(String cardNumber);

    List<CreditCardDto> findAll();
}
