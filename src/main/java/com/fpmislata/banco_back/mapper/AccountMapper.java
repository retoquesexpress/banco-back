package com.fpmislata.banco_back.mapper;

import com.fpmislata.banco_back.controller.webModel.request.AccountRequest;
import com.fpmislata.banco_back.controller.webModel.response.AccountResponse;
import com.fpmislata.banco_back.domain.repository.entity.AccountEntity;
import com.fpmislata.banco_back.domain.service.dto.AccountDto;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.AccountJpaEntity;

import java.util.Optional;

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
                accountJpaEntity.getBalance());
    }

    public AccountJpaEntity fromAccountEntityToAccountJpaEntity(AccountEntity accountEntity) {
        if (accountEntity == null) {
            return null;
        }
        return new AccountJpaEntity(
                accountEntity.iban(),
                accountEntity.balance());
    }

    public AccountResponse fromAccountDtoToAccountResponse(AccountDto accountDto) {
        if (accountDto == null) {
            return null;
        }
        return new AccountResponse(
                accountDto.iban(),
                accountDto.balance());
    }

    public AccountDto fromAccountRequestToAccountDto(AccountRequest accountRequest) {
        if (accountRequest == null) {
            return null;
        }
        return new AccountDto(
                accountRequest.iban(),
                accountRequest.balance());
    }

    public Optional<AccountDto> fromAccountEntityToAccountDto(Optional<AccountEntity> byIban) {
        if (byIban.isEmpty()) {
            return Optional.empty();
        }
        AccountEntity accountEntity = byIban.get();
        AccountDto accountDto = new AccountDto(
                accountEntity.iban(),
                accountEntity.balance());
        return Optional.of(accountDto);
    }

    public AccountEntity fromAccountDtoToAccountEntity(AccountDto accountDto) {
        if (accountDto == null) {
            return null;
        }
        return new AccountEntity(
                accountDto.iban(),
                accountDto.balance());
    }
}
