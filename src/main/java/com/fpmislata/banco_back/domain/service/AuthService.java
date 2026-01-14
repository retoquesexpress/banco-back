package com.fpmislata.banco_back.domain.service;

import com.fpmislata.banco_back.controller.webModel.request.AuthRequest;
import com.fpmislata.banco_back.controller.webModel.request.RegisterRequest;
import com.fpmislata.banco_back.controller.webModel.response.AuthResponse;
import com.fpmislata.banco_back.domain.model.Client;
import com.fpmislata.banco_back.domain.model.Token;


public interface AuthService {
    Client getUserFromToken(Token token);
    Token generateTokenForUser(Client client);
    AuthResponse login(AuthRequest request);
    //AuthResponse register(RegisterRequest request);
}
