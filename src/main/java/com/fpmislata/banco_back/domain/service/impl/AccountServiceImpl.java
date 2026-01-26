package com.fpmislata.banco_back.domain.service.impl;

import com.fpmislata.banco_back.domain.model.Client;
import com.fpmislata.banco_back.domain.repository.AccountRepository;
import com.fpmislata.banco_back.domain.repository.entity.AccountEntity;
import com.fpmislata.banco_back.domain.service.AccountService;
import com.fpmislata.banco_back.domain.service.dto.AccountDto;
import com.fpmislata.banco_back.domain.service.dto.CreditCardDto;
import com.fpmislata.banco_back.exception.BusinessException;
import com.fpmislata.banco_back.exception.ResourceNotFoundException;
import com.fpmislata.banco_back.mapper.AccountMapper;
import com.fpmislata.banco_back.mapper.ClientMapper;
import com.fpmislata.banco_back.mapper.CreditCardMapper;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public Optional<AccountDto> findAccountByCreditCard(CreditCardDto creditCardDto) {
        Optional<AccountDto> account = accountRepository
                .findAccountByCreditCard(
                        CreditCardMapper.getInstance().fromCreditCardDtoToCreditCardEntity(creditCardDto))
                .map(AccountMapper.getInstance()::fromAccountEntityToAccountDto);
        ;
        if (account.isPresent()) {
            return account;
        } else {
            throw new ResourceNotFoundException("Account not found");
        }
    }

    @Override
    @Transactional
    public AccountDto depositMoney(AccountDto accountDto, Double amount, String concept, String cardNumber) {
        validateConcept(concept);

        AccountEntity entity = AccountMapper.getInstance().fromAccountDtoToAccountEntity(accountDto);
        entity = entity.depositMoney(amount);

        AccountEntity updatedEntity = accountRepository.depositMoney(entity, amount, concept, cardNumber);

        return AccountMapper.getInstance().fromAccountEntityToAccountDto(updatedEntity);

    }

    @Override
    @Transactional
    public AccountDto withdrawMoney(AccountDto accountDto, Double amount, String concept, String cardNumber) {
        validateConcept(concept);

        AccountEntity entity = AccountMapper.getInstance().fromAccountDtoToAccountEntity(accountDto);
        entity = entity.withdrawMoney(amount);

        AccountEntity savedEntity = accountRepository.withdrawMoney(entity, amount, concept, cardNumber);

        return AccountMapper.getInstance().fromAccountEntityToAccountDto(savedEntity);
    }

    private void validateConcept(String concept) {
        if (concept == null || concept.trim().length() < 3) {
            throw new BusinessException("El concepto no puede ser null ni menor de 3 caracteres");
        }
    }

}
