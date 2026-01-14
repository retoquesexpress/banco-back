package com.fpmislata.banco_back.domain.service.impl;

import com.fpmislata.banco_back.domain.model.Account;
import com.fpmislata.banco_back.domain.model.CreditCard;
import com.fpmislata.banco_back.domain.repository.ClientRepository;
import com.fpmislata.banco_back.domain.repository.CreditCardRepository;
import com.fpmislata.banco_back.domain.service.CreditCardService;
import com.fpmislata.banco_back.domain.service.dto.ClientDto;
import com.fpmislata.banco_back.domain.service.dto.CreditCardDto;
import com.fpmislata.banco_back.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public CreditCardServiceImpl(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public List<CreditCardDto> findAllCreditCardsByAccount(Account account) {
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
}
