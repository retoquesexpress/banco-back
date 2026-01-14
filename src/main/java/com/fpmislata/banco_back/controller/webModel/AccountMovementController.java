package com.fpmislata.banco_back.controller.webModel;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpmislata.banco_back.controller.webModel.response.AccountMovementDetailResponse;
import com.fpmislata.banco_back.domain.service.AccountMovementService;
import com.fpmislata.banco_back.mapper.AccountMovementMapper;

@RestController
@RequestMapping("/api/movements")
public class AccountMovementController {
    private final AccountMovementService accountMovementService;

    public AccountMovementController(AccountMovementService accountMovementService) {
        this.accountMovementService = accountMovementService;
    }

    @GetMapping
    public ResponseEntity<List<AccountMovementDetailResponse>> getAllAccountMovements() {
        List<AccountMovementDetailResponse> accountMovements = accountMovementService.findAllAccountMovements()
                .stream()
                .map(AccountMovementMapper.getInstance()::fromAccountMovementDtoToAccountResponse)
                .toList();
        return new ResponseEntity<>(accountMovements, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountMovementDetailResponse> getAccountMovementById(@PathVariable Integer id) {
        return accountMovementService.findAccountMovementById(id)
                .map(accountMovementDto -> new ResponseEntity<>(
                        AccountMovementMapper.getInstance().fromAccountMovementDtoToAccountResponse(accountMovementDto),
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
