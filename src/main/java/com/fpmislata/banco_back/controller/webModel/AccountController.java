package com.fpmislata.banco_back.controller.webModel;

import com.fpmislata.banco_back.controller.webModel.response.AccountResponse;
import com.fpmislata.banco_back.domain.service.AccountService;
import com.fpmislata.banco_back.domain.service.ClientService;
import com.fpmislata.banco_back.mapper.AccountMapper;
import com.fpmislata.banco_back.mapper.ClientMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;
    private final ClientService clientService;

    public AccountController(AccountService accountService, ClientService clientService) {
        this.accountService = accountService;
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> findByClient(@RequestParam(required = false) String dni) {
        if (dni == null || dni.isBlank()) {
            List<AccountResponse> accounts = accountService.findAll()
                    .stream()
                    .map(AccountMapper.getInstance()::fromAccountDtoToAccountResponse)
                    .toList();
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        }

        return clientService.getClientByDni(dni)
                .map(clientDto -> {
                    List<AccountResponse> accounts = accountService
                            .findByClient(ClientMapper.getInstance().fromClientDtoToClient(clientDto))
                            .stream()
                            .map(AccountMapper.getInstance()::fromAccountDtoToAccountResponse)
                            .toList();
                    return new ResponseEntity<>(accounts, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{iban}")
    public ResponseEntity<AccountResponse> getByIban(@PathVariable String iban) {
        AccountResponse accountResponse = AccountMapper.getInstance()
                .fromAccountDtoToAccountResponse(accountService.getByIban(iban));
        return new ResponseEntity<>(accountResponse, HttpStatus.OK);
    }

}
