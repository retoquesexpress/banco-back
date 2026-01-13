package com.fpmislata.banco_back.controller.webModel;

import com.fpmislata.banco_back.controller.webModel.response.AccountResponse;
import com.fpmislata.banco_back.domain.service.AccountService;
import com.fpmislata.banco_back.mapper.AccountMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<AccountResponse> findAll() {
        List<AccountResponse> accountResponses = accountService.findByClient()
                .stream()
                .map(AccountMapper.getInstance()::fromAccountDtoToAccountResponse)
                .toList();
        return new ResponseEntity(accountResponses, HttpStatus.OK);
    }

    @GetMapping("/{iban}")
    public ResponseEntity<AccountResponse> getByIban(@PathVariable String iban) {
        AccountResponse accountResponse = AccountMapper.getInstance()
                .fromAccountDtoToAccountResponse(accountService.getByIban(iban));
        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }

}
