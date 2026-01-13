package com.fpmislata.banco_back.controller.webModel;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpmislata.banco_back.controller.webModel.response.AccountMovementDetailResponse;
import com.fpmislata.banco_back.domain.model.AccountMovement;
import com.fpmislata.banco_back.domain.repository.entity.AccountMovementEntity;
import com.fpmislata.banco_back.domain.service.AccountMovementService;
import com.fpmislata.banco_back.mapper.AccountMovementMapper;

@RestController
@RequestMapping("/api/movement")
public class AccountMovementController {
    private final AccountMovementService accountMovementService;

    public AccountMovementController(AccountMovementService accountMovementService) {
        this.accountMovementService = accountMovementService;
    }

    @GetMapping
    public ResponseEntity<AccountMovementDetailResponse> getAllAccountMovements() {
        List<AccountMovementDetailResponse> accountMovements = accountMovementService.findAllAccountMovements()
                .stream()
                .map(AccountMovementMapper.getInstance()::fromAccountMovementDtoToAccountResponse)
                .toList();
        return new ResponseEntity(accountMovements, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountMovementDetailResponse> getAccountMovementById(@PathVariable Integer id) {
        AccountMovementDetailResponse accountMovement = accountMovementService.findAccountMovementById(id)
                .map(AccountMovementMapper.getInstance()::fromAccountMovementDtoToAccountResponse)
                .orElse(null);
        return new ResponseEntity(accountMovement, HttpStatus.OK);
    }

}
