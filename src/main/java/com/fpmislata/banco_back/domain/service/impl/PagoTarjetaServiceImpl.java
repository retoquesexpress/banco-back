package com.fpmislata.banco_back.domain.service.impl;

import com.fpmislata.banco_back.controller.webModel.request.PagoTarjetaRequest;
import com.fpmislata.banco_back.domain.model.Account;
import com.fpmislata.banco_back.domain.service.AccountService;
import com.fpmislata.banco_back.domain.service.ClientService;
import com.fpmislata.banco_back.domain.service.CreditCardService;
import com.fpmislata.banco_back.domain.service.PagoTarjetaService;
import com.fpmislata.banco_back.domain.service.dto.AccountDto;
import com.fpmislata.banco_back.mapper.AccountMapper;

public class PagoTarjetaServiceImpl implements PagoTarjetaService {
    private final ClientService clientService;
    private final AccountService accountService;
    private final CreditCardService creditCardService;

    public PagoTarjetaServiceImpl(ClientService clientService, AccountService accountService,
            CreditCardService creditCardService) {
        this.clientService = clientService;
        this.accountService = accountService;
        this.creditCardService = creditCardService;
    }

    @Override
    public void procesarPagoTarjeta(PagoTarjetaRequest pagoTarjetaRequest) {
        Account accountOrigen = accountService.findAccountByCreditCard(pagoTarjetaRequest.origen())
                .map(AccountMapper.getInstance()::fromAccountDtoToAccount).orElseThrow();
        AccountDto accountDestino = accountService.getByIban(pagoTarjetaRequest.destino().iban());
        clientService.validate(accountOrigen.getClient().getUserName(), accountOrigen.getClient().getApiToken());
        creditCardService.validate(pagoTarjetaRequest.origen());
        accountService.withdrawMoney(AccountMapper.getInstance().fromAccountToAccountDto(accountOrigen),
                pagoTarjetaRequest.pago().importe(), pagoTarjetaRequest.pago().concept(),
                pagoTarjetaRequest.origen().cardNumber());
        accountService.depositMoney(accountDestino,
                pagoTarjetaRequest.pago().importe(), pagoTarjetaRequest.pago().concept(),
                pagoTarjetaRequest.origen().cardNumber());

    }
}
