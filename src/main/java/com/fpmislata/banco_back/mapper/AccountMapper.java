package com.fpmislata.banco_back.mapper;

import com.fpmislata.banco_back.controller.webModel.request.AccountRequest;
import com.fpmislata.banco_back.controller.webModel.response.AccountResponse;
import com.fpmislata.banco_back.domain.model.Account;
import com.fpmislata.banco_back.domain.repository.entity.AccountEntity;
import com.fpmislata.banco_back.domain.service.dto.AccountDto;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.AccountJpaEntity;

import com.fpmislata.banco_back.domain.service.dto.ClientDto;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.AccountJpaEntity;

import java.util.Collections;
import java.util.List;

public class AccountMapper {
    private static AccountMapper INSTANCE;

    private AccountMapper() {
    }

    public static AccountMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountMapper();
        }
        return INSTANCE;
    }

    public AccountEntity fromAccountJpaEntityToAccountEntity(AccountJpaEntity accountJpaEntity) {
        if (accountJpaEntity == null) {
            return null;
        }



        return new AccountEntity(
                accountJpaEntity.getIban(),
                accountJpaEntity.getBalance(),
                ClientMapper.getInstance().fromClientJpaEntityToClientEntity(accountJpaEntity.getClient()),
                Collections.emptyList(),
                accountJpaEntity.getCreditCards().stream()
                        .map(CreditCardMapper.getInstance()::fromCreditCardJpaEntityToCreditCardEntity)
                        .toList());
    }

    public AccountJpaEntity fromAccountEntityToAccountJpaEntity(AccountEntity accountEntity) {
        if (accountEntity == null) {
            return null;
        }
        return new AccountJpaEntity(
                accountEntity.iban(),
                accountEntity.balance(),

                ClientMapper.getInstance().fromClientEntityToClientJpaEntity(accountEntity.client()),
                accountEntity.creditCards().stream()
                        .map(CreditCardMapper.getInstance()::fromCreditCardEntityToCreditCardJpaEntity)
                        .toList());
    }

    public AccountResponse fromAccountDtoToAccountResponse(AccountDto accountDto) {
        if (accountDto == null) {
            return null;
        }
        return new AccountResponse(
                accountDto.iban(),

                accountDto.balance(),
                accountDto.client(),
                accountDto.movements(),
                accountDto.creditCards());
    }

    public AccountDto fromAccountRequestToAccountDto(AccountRequest accountRequest) {
        if (accountRequest == null) {
            return null;
        }
        return new AccountDto(
                accountRequest.iban(),

                accountRequest.balance(),
                null,
                Collections.emptyList(),
                Collections.emptyList());
    }

    public AccountDto toAccountDto(AccountEntity accountEntity, ClientDto clientDto,
            List<com.fpmislata.banco_back.domain.service.dto.AccountMovementDto> movements) {
        if (accountEntity == null) {
            return null;
        }
        return new AccountDto(
                accountEntity.iban(),
                accountEntity.balance(),
                clientDto,
                movements,
                accountEntity.creditCards().stream()
                        .map(CreditCardMapper.getInstance()::fromCreditCardEntityToCreditCardDto)
                        .toList());
    }

    public AccountDto fromAccountEntityToAccountDto(AccountEntity accountEntity) {
        if (accountEntity == null) {
            return null;
        }
        return new AccountDto(
                accountEntity.iban(),

                accountEntity.balance(),
                ClientMapper.getInstance().fromClientEntityToClientDto(accountEntity.client()),
                accountEntity.accountMovements().stream()
                        .map(AccountMovementMapper.getInstance()::fromAccountMovementEntityToAccountDto)
                        .toList(),
                accountEntity.creditCards().stream()
                        .map(CreditCardMapper.getInstance()::fromCreditCardEntityToCreditCardDto)
                        .toList());
    }

    public AccountEntity fromAccountDtoToAccountEntity(AccountDto accountDto) {
        if (accountDto == null) {
            return null;
        }
        return new AccountEntity(
                accountDto.iban(),

                accountDto.balance(),
                ClientMapper.getInstance().fromClientDtoToClientEntity(accountDto.client()),
                Collections.emptyList(),
                Collections.emptyList());
    }

    public Account fromAccountDtoToAccount(AccountDto accountDto) {
        if (accountDto == null) {
            return null;
        }
        Account account = new Account(
                accountDto.iban(),
                accountDto.balance());
        account.setClient(ClientMapper.getInstance().fromClientDtoToClient(accountDto.client()));
        account.setAccountMovements(accountDto.movements().stream()
                .map(AccountMovementMapper.getInstance()::fromAccountMovementDtoToAccountMovement)
                .toList());
        account.setCreditCards(accountDto.creditCards().stream()
                .map(CreditCardMapper.getInstance()::fromCreditCardDtoToCreditCard)
                .toList());
        return account;
    }

    public AccountDto fromAccountToAccountDto(Account account) {
        if (account == null) {
            return null;
        }
        return new AccountDto(
                account.getIban(),
                account.getBalance(),
                ClientMapper.getInstance().fromClientToClientDto(account.getClient()),
                account.getAccountMovements().stream()
                        .map(AccountMovementMapper.getInstance()::fromAccountMovementToAccountMovementDto)
                        .toList(),
                account.getCreditCards().stream()
                        .map(CreditCardMapper.getInstance()::fromCreditCardToCreditCardDto)
                        .toList());
    }
}
