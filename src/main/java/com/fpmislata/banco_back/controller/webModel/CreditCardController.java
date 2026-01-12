package com.fpmislata.banco_back.controller.webModel;

import com.fpmislata.banco_back.controller.webModel.response.CreditCardDetailResponse;
import com.fpmislata.banco_back.domain.model.Account;
import com.fpmislata.banco_back.domain.service.CreditCardService;
import com.fpmislata.banco_back.mapper.CreditCardMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class CreditCardController {
    private final CreditCardService creditCardService;
    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @GetMapping("/api/{cuenta}")
    public ResponseEntity<List<CreditCardDetailResponse>>findAllCreditCardsByAccount(@PathVariable Account account){
        List<CreditCardDetailResponse> creditCardDetailResponseList = creditCardService.findAllCreditCardsByAccount(account)
                .stream().map(CreditCardMapper.getInstance()::fromCreditCardDtoToCreditCardDetailResponse)
                .toList();
        return new ResponseEntity<>(creditCardDetailResponseList, HttpStatus.OK);
    }

    @GetMapping("/{idCreditCard}")
    public ResponseEntity<CreditCardDetailResponse> findById(@PathVariable Integer idCreditCard) {
        CreditCardDetailResponse creditCardDetailResponse = CreditCardMapper.getInstance()
                .fromCreditCardDtoToCreditCardDetailResponse(creditCardService.findCreditCardById(idCreditCard).get());
        return new ResponseEntity<>(creditCardDetailResponse, HttpStatus.OK);
    }


}
