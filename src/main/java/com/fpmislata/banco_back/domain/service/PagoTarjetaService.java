package com.fpmislata.banco_back.domain.service;

import com.fpmislata.banco_back.controller.webModel.request.PagoTarjetaRequest;

public interface PagoTarjetaService {
    void procesarPagoTarjeta(PagoTarjetaRequest pagoTarjetaRequest);
}
