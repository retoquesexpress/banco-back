package com.fpmislata.banco_back.mapper;

import com.fpmislata.banco_back.controller.webModel.response.AccountMovementDetailResponse;
import com.fpmislata.banco_back.domain.model.AccountMovement;
import com.fpmislata.banco_back.domain.repository.entity.AccountMovementEntity;
import com.fpmislata.banco_back.domain.service.dto.AccountMovementDto;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.AccountMovementJpaEntity;

public class AccountMovementMapper {
    private static AccountMovementMapper INSTANCE;

    private AccountMovementMapper() {
    }

    public static AccountMovementMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AccountMovementMapper();
        }
        return INSTANCE;
    }

    public AccountMovementDto fromAccountMovementEntityToAccountDto(AccountMovementEntity accountMovementEntity) {
        if (accountMovementEntity == null) {
            return null;
        }
        return new AccountMovementDto(
                accountMovementEntity.idAccountMovent(),
                accountMovementEntity.creditCardOrigin(),
                accountMovementEntity.originMovement(),
                accountMovementEntity.date(),
                accountMovementEntity.amount(),
                accountMovementEntity.movementType(),
                accountMovementEntity.concept());
    }

    public AccountMovementEntity fromAccountMovementJpaEntityToAccountMovementEntity(
            AccountMovementJpaEntity accountMovementJpaEntity) {
        if (accountMovementJpaEntity == null) {
            return null;
        }
        return new AccountMovementEntity(
                accountMovementJpaEntity.getIdAccountMovement(),
                accountMovementJpaEntity.getCreditCardOrigin(),
                accountMovementJpaEntity.getOriginMovement(),
                accountMovementJpaEntity.getDate(),
                accountMovementJpaEntity.getAmount(),
                accountMovementJpaEntity.getMovementType(),
                accountMovementJpaEntity.getConcept());
    }

    public AccountMovementDetailResponse fromAccountMovementDtoToAccountResponse(
            AccountMovementDto accountMovementDto) {
        if (accountMovementDto == null) {
            return null;
        }
        return new AccountMovementDetailResponse(
                accountMovementDto.idAccountMovement(),
                accountMovementDto.creditCardOrigin(),
                accountMovementDto.originMovement(),
                accountMovementDto.date(),
                accountMovementDto.amount(),
                accountMovementDto.movementType(),
                accountMovementDto.concept());
    }

    public AccountMovement fromAccountMovementDtoToAccountMovement(
            AccountMovementDto accountMovementDto) {
        if (accountMovementDto == null) {
            return null;
        }
        return new AccountMovement(
                accountMovementDto.idAccountMovement(),
                accountMovementDto.creditCardOrigin(),
                accountMovementDto.originMovement(),
                accountMovementDto.date(),
                accountMovementDto.amount(),
                accountMovementDto.movementType(),
                accountMovementDto.concept());
    }

    public AccountMovementJpaEntity fromAccountMovementEntityToAccountMovementJpaEntity(
            AccountMovementEntity accountMovementEntity) {
        if (accountMovementEntity == null) {
            return null;
        }
        return new AccountMovementJpaEntity(
                accountMovementEntity.idAccountMovent(),
                accountMovementEntity.creditCardOrigin(),
                accountMovementEntity.originMovement(),
                accountMovementEntity.date(),
                accountMovementEntity.amount(),
                accountMovementEntity.movementType(),
                accountMovementEntity.concept());
    }

    public AccountMovementDto fromAccountMovementToAccountMovementDto(AccountMovement accountMovement) {
        if (accountMovement == null) {
            return null;
        }
        return new AccountMovementDto(
                accountMovement.getIdAccountMovement(),
                accountMovement.getCreditCardOrigin(),
                accountMovement.getOriginMovement(),
                accountMovement.getDate(),
                accountMovement.getAmount(),
                accountMovement.getMovementType(),
                accountMovement.getConcept());
    }
}
