package com.fpmislata.banco_back.persistence.repository;

import com.fpmislata.banco_back.domain.repository.CreditCardRepository;
import com.fpmislata.banco_back.domain.service.dto.AccountDto;
import com.fpmislata.banco_back.domain.service.dto.CreditCardDto;
import com.fpmislata.banco_back.mapper.CreditCardMapper;
import com.fpmislata.banco_back.persistence.dao.jpa.CreditCardJpaDao;

import java.util.List;
import java.util.Optional;

public class CreditCardRepositoryImpl implements CreditCardRepository {

    private final CreditCardJpaDao creditCardJpaDao;

    public CreditCardRepositoryImpl(CreditCardJpaDao creditCardJpaDao) {
        this.creditCardJpaDao = creditCardJpaDao;
    }

    @Override
    public List<CreditCardDto> findAllCreditCardsByAccount(AccountDto account) {
        return creditCardJpaDao.findAllCreditCardsByAccount(account).stream()
                .map(CreditCardMapper.getInstance()::fromCreditCardJpaEntityToCreditCardDto).toList();

    }

    @Override
    public Optional<CreditCardDto> findCreditCardById(Integer id) {
        return creditCardJpaDao.findCreditCardById(id)
                .map(CreditCardMapper.getInstance()::fromCreditCardJpaEntityToCreditCardDto);
    }

    @Override
    public Optional<CreditCardDto> findCreditCardByCardNumber(String cardNumber) {
        return creditCardJpaDao.findCreditCardByCardNumber(cardNumber)
                .map(CreditCardMapper.getInstance()::fromCreditCardJpaEntityToCreditCardDto);
    }

    @Override
    public List<CreditCardDto> findAll() {
        return creditCardJpaDao.findAll().stream()
                .map(CreditCardMapper.getInstance()::fromCreditCardJpaEntityToCreditCardDto)
                .toList();
    }
}
