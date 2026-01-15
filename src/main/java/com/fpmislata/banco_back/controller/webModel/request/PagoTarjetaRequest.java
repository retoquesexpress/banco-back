package com.fpmislata.banco_back.controller.webModel.request;

import com.fpmislata.banco_back.domain.service.dto.AutorizacionDto;
import com.fpmislata.banco_back.domain.service.dto.DestinoDto;
import com.fpmislata.banco_back.domain.service.dto.PagoDto;
import com.fpmislata.banco_back.domain.service.dto.TarjetaDto;

public record PagoTarjetaRequest (
        AutorizacionDto autorizacion,
        TarjetaDto origen,
        DestinoDto destino,
        PagoDto pago
){
}
