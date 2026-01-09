package com.fpmislata.banco_back.domain.service.impl;


import com.fpmislata.banco_back.domain.repository.AccountRepository;
import com.fpmislata.banco_back.domain.repository.entity.AccountEntity;
import com.fpmislata.banco_back.domain.service.AccountService;
import com.fpmislata.banco_back.domain.service.dto.AccountDto;
import com.fpmislata.banco_back.exception.ResourceNotFoundException;
import com.fpmislata.banco_back.mapper.AccountMapper;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public List<AccountDto> findByClient() {
        if(accountRepository.findByClient().isEmpty()){
            throw new ResourceNotFoundException("No accounts found");
        }
        return accountRepository.findByClient()
                .stream()
                .map(AccountMapper.getInstance()::fromAccountEntityToAccountDto)
                .toList();
    }

    @Override
    public AccountDto getByIban(String iban) {
        Optional<AccountDto> account = AccountMapper.getInstance().fromAccountEntityToAccountDto(accountRepository.findByIban(iban));
        if(account.isEmpty()){
            throw new ResourceNotFoundException("Account not found");
        }
        return account.get();
    }

    @Override
    public Optional<AccountDto> findByIban(String iban) {
        Optional<AccountDto> account = AccountMapper.getInstance().fromAccountEntityToAccountDto (accountRepository.findByIban(iban));
        if(account.isEmpty()){
            throw new ResourceNotFoundException("Account not found");
        }
        return account;
    }
}
