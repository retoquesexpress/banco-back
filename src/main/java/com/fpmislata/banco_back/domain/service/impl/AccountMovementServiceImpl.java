package com.fpmislata.banco_back.domain.service.impl;

import com.fpmislata.banco_back.domain.repository.AccountMovementRepository;
import com.fpmislata.banco_back.domain.service.AccountMovementService;
import com.fpmislata.banco_back.domain.service.dto.AccountMovementDto;
import com.fpmislata.banco_back.mapper.AccountMovementMapper;

import java.util.List;
import java.util.Optional;

public class AccountMovementServiceImpl implements AccountMovementService {
    private final AccountMovementRepository accountMovementRepository;

    public AccountMovementServiceImpl(AccountMovementRepository accountMovementRepository) {
        this.accountMovementRepository = accountMovementRepository;
    }

    @Override
    public List<AccountMovementDto> findAllAccountMovements() {
        if (accountMovementRepository.findAllAccountMovements().isEmpty()) {
            throw new RuntimeException("No account movements found");
        }
        return accountMovementRepository.findAllAccountMovements().stream()
                .map(AccountMovementMapper.getInstance()::fromAccountMovementEntityToAccountDto)
                .toList();
    }

    @Override
    public AccountMovementDto getAccountMovementById(Integer idAccountMovement) {
        Optional<AccountMovementDto> account = accountMovementRepository.findAccountMovementById(idAccountMovement)
                .map(AccountMovementMapper.getInstance()::fromAccountMovementEntityToAccountDto);
        if (account.isEmpty()) {
            throw new RuntimeException("Account movement not found with id: " + idAccountMovement);
        }
        return account.get();
    }

    @Override
    public Optional<AccountMovementDto> findAccountMovementById(Integer idAccountMovement) {
        return accountMovementRepository.findAccountMovementById(idAccountMovement)
                .map(AccountMovementMapper.getInstance()::fromAccountMovementEntityToAccountDto);
    }
}
