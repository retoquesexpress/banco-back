package com.fpmislata.banco_back.controller.webModel;

import com.fpmislata.banco_back.controller.webModel.request.ClientInsertRequest;
import com.fpmislata.banco_back.controller.webModel.response.ClientDetailResponse;
import com.fpmislata.banco_back.domain.service.ClientService;
import com.fpmislata.banco_back.domain.service.dto.ClientDto;
import com.fpmislata.banco_back.mapper.ClientMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientDetailResponse>> findAllClients() {
        List<ClientDetailResponse> clientDetailResponse = clientService.findAllClients()
                .stream()
                .map(ClientMapper.getInstance()::fromClientDtoToClientDetailResponse)
                .toList();
        return new ResponseEntity<>(clientDetailResponse, HttpStatus.OK);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<ClientDetailResponse> getByDni(@PathVariable String dni) {
        Optional<ClientDto> clientDto = clientService.getClientByDni(dni);
        ClientDetailResponse clientDetailResponse = ClientMapper.getInstance()
                .fromClientDtoToClientDetailResponse(clientDto.orElse(null));
        return new ResponseEntity<>(clientDetailResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDetailResponse> create(@RequestBody ClientInsertRequest clientInsertRequest) {
        ClientDto clientDto = new ClientDto(
                clientInsertRequest.dni(),
                clientInsertRequest.userName(),
                clientInsertRequest.password(),
                clientInsertRequest.name(),
                clientInsertRequest.surname1(),
                clientInsertRequest.surname2(),
                clientInsertRequest.apiToken());

        ClientDto createdClient = clientService.create(clientDto);
        ClientDetailResponse createdClientResponse = ClientMapper.getInstance()
                .fromClientDtoToClientDetailResponse(createdClient);

        return new ResponseEntity<>(createdClientResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{dni}")
    public ResponseEntity<ClientDetailResponse> update(@PathVariable String dni,
            @RequestBody ClientInsertRequest clientInsertRequest) {

        ClientDto clientDto = new ClientDto(
                clientInsertRequest.dni(),
                clientInsertRequest.userName(),
                clientInsertRequest.password(),
                clientInsertRequest.name(),
                clientInsertRequest.surname1(),
                clientInsertRequest.surname2(),
                clientInsertRequest.apiToken()

        );

        ClientDto updatedClient = clientService.update(clientDto);
        ClientDetailResponse updatedClientResponse = ClientMapper.getInstance()
                .fromClientDtoToClientDetailResponse(updatedClient);

        return new ResponseEntity<>(updatedClientResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> delete(@PathVariable String dni) {
        clientService.delete(dni);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
