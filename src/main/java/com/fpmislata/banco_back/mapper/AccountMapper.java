package com.fpmislata.banco_back.mapper;

import com.fpmislata.banco_back.controller.webModel.request.AccountRequest;
import com.fpmislata.banco_back.controller.webModel.response.AccountResponse;
import com.fpmislata.banco_back.domain.repository.entity.AccountEntity;
import com.fpmislata.banco_back.domain.service.dto.AccountDto;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.AccountJpaEntity;

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
                accountEntity.balance(),
                null); // Note: Manual client assignment might be needed elsewhere
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

    public AccountDto fromAccountEntityToAccountDto(AccountEntity accountEntity) {
        if (accountEntity == null) {
            return null;
        }
        return new AccountDto(
                accountEntity.iban(),
                accountEntity.balance());
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
