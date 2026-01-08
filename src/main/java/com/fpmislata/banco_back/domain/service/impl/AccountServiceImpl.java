package com.fpmislata.banco_back.domain.service.impl;

import com.fpmislata.banco_back.domain.service.AccountService;
import com.fpmislata.banco_back.domain.service.dto.AccountDto;
import com.fpmislata.banco_back.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class AccountServiceImpl implements AccountService {
    private final AccountService accountService;
    public AccountServiceImpl(AccountService accountService) {
        this.accountService = accountService;
    }


    @Override
    public List<AccountDto> findAll() {
        if(accountService.findAll().isEmpty()){
            throw new ResourceNotFoundException("No accounts found");
        }
        return accountService.findAll();
    }

    @Override
    public AccountDto getByIban(String iban) {
        Optional<AccountDto> account = accountService.findByIban(iban);
        if(account.isEmpty()){
            throw new ResourceNotFoundException("Account not found");
        }
        return account.get();
    }

    @Override
    public Optional<AccountDto> findByIban(String iban) {
        Optional<AccountDto> account = accountService.findByIban(iban);
        if(account.isEmpty()){
            throw new ResourceNotFoundException("Account not found");
        }
        return account;
    }

    @Transactional
    @Override
    public AccountDto create(AccountDto accountDto) {
        return accountService.create(accountDto);
    }

    @Transactional
    @Override
    public AccountDto update(AccountDto accountDto) {
        Optional<AccountDto> account = accountService.findByIban(accountDto.iban());
        if(account.isEmpty()){
            throw new ResourceNotFoundException("Account not found");
        }
        return  accountService.update(accountDto);
    }
    
    @Transactional
    @Override
    public void deleteById(Integer idAccount) {
        Optional<AccountDto> account = accountService.findByIban(idAccount.toString());
        if(account.isEmpty()){
            throw new ResourceNotFoundException("Account not found");
        }
        accountService.deleteById(idAccount);
    }
}
