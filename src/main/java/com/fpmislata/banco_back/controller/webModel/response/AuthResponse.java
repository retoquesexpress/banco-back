package com.fpmislata.banco_back.controller.webModel.response;

import com.fpmislata.banco_back.domain.service.dto.ClientDto;

import java.time.LocalDateTime;

public record AuthResponse(
        String token,
        LocalDateTime expirationDate,
        ClientDto clientDto)
{}
