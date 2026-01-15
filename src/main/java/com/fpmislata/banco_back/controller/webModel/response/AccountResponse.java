package com.fpmislata.banco_back.controller.webModel.response;


import com.fpmislata.banco_back.domain.service.dto.AccountMovementDto;
import com.fpmislata.banco_back.domain.service.dto.ClientDto;
import com.fpmislata.banco_back.domain.service.dto.CreditCardDto;

import java.util.List;

public record AccountResponse(
                String iban,
                Double balance,
                ClientDto client,
                List<AccountMovementDto> movements,
                List<CreditCardDto> creditCards) {
}
