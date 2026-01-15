package com.fpmislata.banco_back.controller.webModel;

import com.fpmislata.banco_back.controller.webModel.request.PagoTarjetaRequest;
import com.fpmislata.banco_back.domain.service.PagoTarjetaService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pagos")
public class PagoTarjetaController {

    private final PagoTarjetaService pagoTarjetaService;

    public PagoTarjetaController(PagoTarjetaService pagoTarjetaService) {
        this.pagoTarjetaService = pagoTarjetaService;
    }

    @PostMapping("/tarjeta")
    public ResponseEntity<Void> pagarTarjeta(@RequestBody PagoTarjetaRequest pagoTarjetaRequest) {
        pagoTarjetaService.procesarPagoTarjeta(pagoTarjetaRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
