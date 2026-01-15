package com.fpmislata.banco_back.controller.webModel.request;

import com.fpmislata.banco_back.domain.service.dto.*;

public record PagoTarjetaRequest (
        AutorizacionDto autorizacion,
        CreditCardDto origen,
        DestinoDto destino,
        PagoDto pago
){
}
