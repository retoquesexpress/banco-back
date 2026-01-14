package com.fpmislata.banco_back.mapper;

import com.fpmislata.banco_back.controller.webModel.response.CreditCardDetailResponse;
import com.fpmislata.banco_back.domain.service.dto.CreditCardDto;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.CreditCardJpaEntity;

public class CreditCardMapper {
    private static CreditCardMapper instance;

    private CreditCardMapper() {
    }

    public static CreditCardMapper getInstance() {
        if (instance == null) {
            instance = new CreditCardMapper();
        }
        return instance;
    }

    public CreditCardDto fromCreditCardJpaEntityToCreditCardDto(CreditCardJpaEntity creditCardJpaEntity) {
        if (creditCardJpaEntity == null) {
            return null;
        }
        return new CreditCardDto(
                creditCardJpaEntity.getIdCreditCard(),
                creditCardJpaEntity.getCardNumber(),
                creditCardJpaEntity.getExpirationDate(),
                creditCardJpaEntity.getCvv(),
                creditCardJpaEntity.getNombreCompleto());
    }

    public CreditCardJpaEntity fromCreditCardDtoToCreditCardJpaEntity(CreditCardDto creditCardDto) {
        if (creditCardDto == null) {
            return null;
        }
        return new CreditCardJpaEntity(
                creditCardDto.idCreditCard(),
                creditCardDto.cardNumber(),
                creditCardDto.expirationDate(),
                creditCardDto.cvv(),
                creditCardDto.nombreCompleto(),
                null);
    }

    public CreditCardDetailResponse fromCreditCardDtoToCreditCardDetailResponse(CreditCardDto creditCardDto) {
        if (creditCardDto == null) {
            return null;
        }
        return new CreditCardDetailResponse(
                creditCardDto.idCreditCard(),
                creditCardDto.cardNumber(),
                creditCardDto.expirationDate(),
                creditCardDto.cvv(),
                creditCardDto.nombreCompleto());
    }

    public com.fpmislata.banco_back.domain.repository.entity.CreditCardEntity fromCreditCardJpaEntityToCreditCardEntity(
            CreditCardJpaEntity creditCardJpaEntity) {
        if (creditCardJpaEntity == null) {
            return null;
        }
        return new com.fpmislata.banco_back.domain.repository.entity.CreditCardEntity(
                creditCardJpaEntity.getIdCreditCard(),
                creditCardJpaEntity.getCardNumber(),
                creditCardJpaEntity.getExpirationDate(),
                creditCardJpaEntity.getCvv(),
                creditCardJpaEntity.getNombreCompleto());
    }

    public CreditCardJpaEntity fromCreditCardEntityToCreditCardJpaEntity(
            com.fpmislata.banco_back.domain.repository.entity.CreditCardEntity creditCardEntity) {
        if (creditCardEntity == null) {
            return null;
        }
        return new CreditCardJpaEntity(
                creditCardEntity.idCreditCard(),
                creditCardEntity.cardNumber(),
                creditCardEntity.expirationDate(),
                creditCardEntity.cvv(),
                creditCardEntity.nombreCompleto(),
                null);
    }

    public CreditCardDto fromCreditCardEntityToCreditCardDto(
            com.fpmislata.banco_back.domain.repository.entity.CreditCardEntity creditCardEntity) {
        if (creditCardEntity == null) {
            return null;
        }
        return new CreditCardDto(
                creditCardEntity.idCreditCard(),
                creditCardEntity.cardNumber(),
                creditCardEntity.expirationDate(),
                creditCardEntity.cvv(),
                creditCardEntity.nombreCompleto());
    }

    public com.fpmislata.banco_back.domain.repository.entity.CreditCardEntity fromCreditCardDtoToCreditCardEntity(
            CreditCardDto creditCardDto) {
        if (creditCardDto == null) {
            return null;
        }
        return new com.fpmislata.banco_back.domain.repository.entity.CreditCardEntity(
                creditCardDto.idCreditCard(),
                creditCardDto.cardNumber(),
                creditCardDto.expirationDate(),
                creditCardDto.cvv(),
                creditCardDto.nombreCompleto());
    }

    public com.fpmislata.banco_back.domain.model.CreditCard fromCreditCardDtoToCreditCard(
            CreditCardDto creditCardDto) {
        if (creditCardDto == null) {
            return null;
        }
        return new com.fpmislata.banco_back.domain.model.CreditCard(
                creditCardDto.idCreditCard(),
                creditCardDto.cardNumber(),
                creditCardDto.expirationDate(),
                creditCardDto.cvv(),
                creditCardDto.nombreCompleto());
    }
}
