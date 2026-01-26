package com.fpmislata.banco_back.controller.webModel;

import com.fpmislata.banco_back.controller.webModel.response.CreditCardDetailResponse;
import com.fpmislata.banco_back.domain.model.Account;
import com.fpmislata.banco_back.domain.service.AccountService;
import com.fpmislata.banco_back.domain.service.CreditCardService;
import com.fpmislata.banco_back.domain.service.dto.AccountDto;

import com.fpmislata.banco_back.mapper.AccountMapper;
import com.fpmislata.banco_back.mapper.CreditCardMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/credit-cards")
public class CreditCardController {
    private final CreditCardService creditCardService;
    private final AccountService accountService;

    public CreditCardController(CreditCardService creditCardService, AccountService accountService) {
        this.creditCardService = creditCardService;
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<CreditCardDetailResponse>> findAllCreditCardsByAccount(
            @RequestParam(required = false) String iban) {
        if (iban == null || iban.isBlank()) {

            List<CreditCardDetailResponse> creditCards = creditCardService.findAll()
                    .stream()
                    .map(CreditCardMapper.getInstance()::fromCreditCardDtoToCreditCardDetailResponse)
                    .toList();
            return new ResponseEntity<>(creditCards, HttpStatus.OK);

        }

        Account account = AccountMapper.getInstance().fromAccountDtoToAccount(accountService.getByIban(iban));
        List<CreditCardDetailResponse> creditCardDetailResponseList = creditCardService
                .findAllCreditCardsByAccount(AccountMapper.getInstance().fromAccountToAccountDto(account))

                .stream().map(CreditCardMapper.getInstance()::fromCreditCardDtoToCreditCardDetailResponse)
                .toList();
        return new ResponseEntity<>(creditCardDetailResponseList, HttpStatus.OK);
    }

    @GetMapping("/{idCreditCard}")
    public ResponseEntity<CreditCardDetailResponse> findById(@PathVariable Integer idCreditCard) {
        return creditCardService.findCreditCardById(idCreditCard)
                .map(creditCardDto -> new ResponseEntity<>(
                        CreditCardMapper.getInstance().fromCreditCardDtoToCreditCardDetailResponse(creditCardDto),
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
