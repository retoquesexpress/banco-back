package com.fpmislata.banco_back.domain.service.impl;


import com.fpmislata.banco_back.controller.webModel.request.AuthRequest;
import com.fpmislata.banco_back.controller.webModel.request.RegisterRequest;
import com.fpmislata.banco_back.controller.webModel.response.AuthResponse;
import com.fpmislata.banco_back.domain.model.Client;
import com.fpmislata.banco_back.domain.model.Token;
import com.fpmislata.banco_back.domain.repository.AuthRepository;
import com.fpmislata.banco_back.domain.repository.entity.ClientEntity;
import com.fpmislata.banco_back.domain.service.AuthService;
import com.fpmislata.banco_back.domain.service.dto.ClientDto;
import com.fpmislata.banco_back.mapper.ClientMapper;
import com.fpmislata.banco_back.mapper.TokenMapper;
import com.fpmislata.banco_back.util.JwtUtil;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;

    public AuthServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public Client getUserFromToken(Token token) {
        if (!JwtUtil.validateToken(token.getToken())) {
            throw new RuntimeException("Invalid or expired token");
        }
        String clientId = JwtUtil.extractClientId(token.getToken());

        ClientEntity clientEntity = authRepository.findByDni(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        ClientDto clientDto = ClientMapper.getInstance().fromClientEntityToClientDto(clientEntity);
        Client client = ClientMapper.getInstance().fromClientDtoToClient(clientDto);
        return client;
    }

    @Override
    public Token generateTokenForUser(Client client) {
        String tokenString = JwtUtil.generateToken(client);
        LocalDateTime expirationDate = JwtUtil.getExpirationTime();
        return TokenMapper.getInstance().fromStringToToken(tokenString, expirationDate);
    }

    @Transactional
    @Override
    public AuthResponse login(AuthRequest request) {
        ClientEntity clientEntity = authRepository.findByUsername(request.userName())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        if (!clientEntity.password().equals(request.password())) {
            throw new RuntimeException("Invalid credentials");
        }

        ClientDto clientDto = ClientMapper.getInstance().fromClientEntityToClientDto(clientEntity);
        Client client = ClientMapper.getInstance().fromClientDtoToClient(clientDto);

        Token token = generateTokenForUser(client);
        client.setPassword(null);
        AuthResponse response = new AuthResponse(token.getToken(), token.getExpirationDate(),ClientMapper.getInstance().fromClientToClientDto(client));

        return response;
    }

}
