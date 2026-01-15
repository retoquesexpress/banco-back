package com.fpmislata.banco_back.domain.service;

import com.fpmislata.banco_back.domain.model.Account;
import com.fpmislata.banco_back.domain.service.dto.AccountDto;
import com.fpmislata.banco_back.domain.service.dto.ClientDto;
import com.fpmislata.banco_back.domain.service.dto.CreditCardDto;
import com.fpmislata.banco_back.domain.service.dto.TarjetaDto;

import java.util.List;
import java.util.Optional;

public interface CreditCardService {
    List<CreditCardDto> findAllCreditCardsByAccount(AccountDto account);

    Optional<CreditCardDto> findCreditCardById(Integer id);

    List<CreditCardDto> findAll();
    // CreditCardDto create(Account account, CreditCardDto creditCardDto);

}
