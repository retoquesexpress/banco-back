package com.fpmislata.banco_back.domain.service.impl;

import com.fpmislata.banco_back.domain.model.Client;
import com.fpmislata.banco_back.domain.repository.AccountRepository;
import com.fpmislata.banco_back.domain.repository.entity.AccountEntity;
import com.fpmislata.banco_back.domain.service.AccountService;
import com.fpmislata.banco_back.domain.service.dto.AccountDto;
import com.fpmislata.banco_back.exception.ResourceNotFoundException;
import com.fpmislata.banco_back.mapper.AccountMapper;
import com.fpmislata.banco_back.mapper.ClientMapper;

import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountMapper.getInstance()::fromAccountEntityToAccountDto)
                .toList();
    }

    @Override
    public List<AccountDto> findByClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }
        List<AccountDto> accounts = accountRepository
                .findByClient(ClientMapper.getInstance().fromClientToClientEntity(client))
                .stream()
                .map(AccountMapper.getInstance()::fromAccountEntityToAccountDto)
                .toList();
        if (accounts.isEmpty()) {
            throw new ResourceNotFoundException("Accounts not found for the given client");
        }
        return accounts;
    }

    @Override
    public AccountDto getByIban(String iban) {
        AccountEntity accountEntity = accountRepository.findByIban(iban)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found"));
        return AccountMapper.getInstance().fromAccountEntityToAccountDto(accountEntity);
    }

    @Override
    public Optional<AccountDto> findByIban(String iban) {
        return accountRepository.findByIban(iban).map(AccountMapper.getInstance()::fromAccountEntityToAccountDto);
    }
}
