package com.fpmislata.banco_back.domain.service.impl;

import com.fpmislata.banco_back.domain.repository.CreditCardRepository;
import com.fpmislata.banco_back.domain.service.CreditCardService;
import com.fpmislata.banco_back.domain.service.dto.AccountDto;
import com.fpmislata.banco_back.domain.service.dto.CreditCardDto;
import com.fpmislata.banco_back.exception.ResourceNotFoundException;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public CreditCardServiceImpl(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public List<CreditCardDto> findAllCreditCardsByAccount(AccountDto account) {
        List<CreditCardDto> creditCards = creditCardRepository.findAllCreditCardsByAccount(account);
        if (creditCards.isEmpty()) {
            throw new ResourceNotFoundException("Credit Card Not Found");
        }
        return creditCards;
    }

    @Override
    public Optional<CreditCardDto> findCreditCardById(Integer id) {
        Optional<CreditCardDto> creditCardDto = creditCardRepository.findCreditCardById(id);
        if (creditCardDto.isPresent()) {
            return creditCardDto;
        } else {
            throw new ResourceNotFoundException("Credit Card not found");
        }
    }

    @Override
    public List<CreditCardDto> findAll() {
        return creditCardRepository.findAll();
    }

    @Override
    public void validate(CreditCardDto origen) {
        CreditCardDto creditCardDto = creditCardRepository.findCreditCardByCardNumber(origen.cardNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Credit Card not found"));
        if (creditCardDto.expirationDate().isBefore(LocalDate.now())) {
            throw new ResourceNotFoundException("Credit Card has expired");
        }
        if (!creditCardDto.cvv().equals(origen.cvv())) {
            throw new ResourceNotFoundException("Invalid CVV");
        }
        if (!creditCardDto.cardNumber().equals(origen.cardNumber())) {
            throw new ResourceNotFoundException("Invalid Card Number");
        }
        if (!creditCardDto.nombreCompleto().equals(origen.nombreCompleto())) {
            throw new ResourceNotFoundException("Invalid Holder Name");
        }
    }
}
