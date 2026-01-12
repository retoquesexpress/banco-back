package com.fpmislata.banco_back.domain.service;

import com.fpmislata.banco_back.domain.service.dto.AccountMovementDto;

import java.util.List;
import java.util.Optional;

public interface AccountMovementService {
    List<AccountMovementDto> findAllAccountMovements();
    AccountMovementDto getAccountMovementById(Integer idAccountMovement);
    Optional<AccountMovementDto> findAccountMovementById(Integer idAccountMovement);
}
