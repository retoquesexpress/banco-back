package com.fpmislata.banco_back.controller.webModel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpmislata.banco_back.controller.webModel.request.AuthRequest;
import com.fpmislata.banco_back.controller.webModel.response.AuthResponse;
import com.fpmislata.banco_back.domain.service.AuthService;
import com.fpmislata.banco_back.domain.service.ClientService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @MockBean
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should login successfully")
    void shouldLoginSuccessfully() throws Exception {
        AuthRequest loginRequest = new AuthRequest("jdoe", "pass");
        AuthResponse authResponse = new AuthResponse("token123", LocalDateTime.now().plusHours(1),
                null);

        when(authService.login(any(AuthRequest.class))).thenReturn(authResponse);

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("token123"))
                .andExpect(jsonPath("$.username").value("jdoe"));
    }

    @Test
    @DisplayName("Should return 404 when user not found")
    void shouldReturnNotFoundWhenUserMismatch() throws Exception {
        AuthRequest loginRequest = new AuthRequest("unknown", "pass");
        when(authService.login(any(AuthRequest.class))).thenThrow(new RuntimeException("User not found"));

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should return 401 when invalid credentials")
    void shouldReturnUnauthorizedWhenInvalidCreds() throws Exception {
        AuthRequest loginRequest = new AuthRequest("jdoe", "wrongpass");
        when(authService.login(any(AuthRequest.class))).thenThrow(new RuntimeException("Invalid credentials"));

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized());
    }
}
