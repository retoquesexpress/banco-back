package com.fpmislata.banco_back.domain.service.impl;

import com.fpmislata.banco_back.domain.repository.ClientRepository;
import com.fpmislata.banco_back.domain.service.ClientService;
import com.fpmislata.banco_back.domain.service.dto.ClientDto;
import com.fpmislata.banco_back.exception.BusinessException;
import com.fpmislata.banco_back.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientDto> findAllClients() {
        return clientRepository.findAllClients();
    }

    @Override
    public Optional<ClientDto> findClientByDni(String dni) {
        Optional<ClientDto> client = clientRepository.findClientByDni(dni);
        if (client.isPresent()) {
            return client;
        } else {
            throw new ResourceNotFoundException("Client not found");
        }
    }

    @Override
    public Optional<ClientDto> getClientByDni(String dni) {
        Optional<ClientDto> client = clientRepository.findClientByDni(dni);
        if (client.isEmpty()) {
            throw new ResourceNotFoundException("Client not found");
        }
        return Optional.of(client.get());
    }

    @Override
    public Optional<ClientDto> findClientByUserName(String userName) {
        Optional<ClientDto> client = clientRepository.findClientByUserName(userName);
        if (client.isPresent()) {
            return client;
        } else {
            throw new ResourceNotFoundException("Client not found");
        }
    }

    @Transactional
    @Override
    public void delete(String dni) {
        Optional<ClientDto> user = clientRepository.findClientByDni(dni);
        if (user.isPresent()) {
            clientRepository.delete(dni);
        } else {
            throw new ResourceNotFoundException("Client does not exists");
        }
    }

    @Transactional
    @Override
    public ClientDto create(ClientDto clientDto) {
        Optional<ClientDto> client = clientRepository.findClientByDni(clientDto.dni());
        if (client.isEmpty()) {
            throw new ResourceNotFoundException("Service not found");
        }
        return clientRepository.create(clientDto);
    }

    @Transactional
    @Override
    public ClientDto update(ClientDto clientDto) {
        Optional<ClientDto> client = clientRepository.findClientByDni(clientDto.dni());
        if (client.isEmpty()) {
            throw new ResourceNotFoundException("Service not found");
        }
        return clientRepository.update(clientDto);
    }

    @Override
    public void validate(String userName, String apiToken) {
        Optional<ClientDto> client = clientRepository.findClientByUserName(userName);
        if (client.isEmpty()) {
            throw new ResourceNotFoundException("Client not found");
        }
        if (!client.get().apiToken().equals(apiToken)) {
            throw new ResourceNotFoundException("Invalid api token");
        }

    }
}
