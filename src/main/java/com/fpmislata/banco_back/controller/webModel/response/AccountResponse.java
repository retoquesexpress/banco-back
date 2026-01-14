package com.fpmislata.banco_back.controller.webModel.response;

import com.fpmislata.banco_back.domain.service.dto.AccountMovementDto;
import com.fpmislata.banco_back.domain.service.dto.ClientDto;

import java.util.List;

public record AccountResponse(
                String iban,
                Double balance,
                ClientDto client,
                List<AccountMovementDto> movements,
                List<com.fpmislata.banco_back.domain.service.dto.CreditCardDto> creditCards) {
}
