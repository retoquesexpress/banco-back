package com.fpmislata.banco_back.domain.service;

import com.fpmislata.banco_back.domain.model.Client;
import com.fpmislata.banco_back.domain.service.dto.AccountDto;
import com.fpmislata.banco_back.domain.service.dto.CreditCardDto;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<AccountDto> findByClient(Client client);

    List<AccountDto> findAll();

    AccountDto getByIban(String iban);

    Optional<AccountDto> findByIban(String iban);

    Optional<AccountDto> findAccountByCreditCard(CreditCardDto creditCardDto);

    AccountDto depositMoney(AccountDto accountDto, Double amount, String concept, String cardNumber);

    AccountDto withdrawMoney(AccountDto accountDto, Double amount, String concept, String cardNumber);

}
