package com.fpmislata.banco_back.domain.service.impl;

import com.fpmislata.banco_back.controller.webModel.request.AuthRequest;
import com.fpmislata.banco_back.controller.webModel.response.AuthResponse;
import com.fpmislata.banco_back.domain.model.Client;
import com.fpmislata.banco_back.domain.model.Token;
import com.fpmislata.banco_back.domain.repository.AuthRepository;
import com.fpmislata.banco_back.domain.repository.entity.ClientEntity;
import com.fpmislata.banco_back.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private AuthRepository authRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    private ClientEntity clientEntity;
    private Client client;

    @BeforeEach
    void setUp() {
        clientEntity = new ClientEntity("12345678A", "jdoe", "password", "John", "Doe", "Smith", "token123");
        client = new Client("12345678A", "jdoe", "password", "John", "Doe", "Smith", "token123");
    }

    @Nested
    @DisplayName("Tests for login")
    class LoginTests {
        @Test
        @DisplayName("Should login successfully")
        void shouldLoginSuccessfully() {
            AuthRequest request = new AuthRequest("jdoe", "password");
            when(authRepository.findByUsername("jdoe")).thenReturn(Optional.of(clientEntity));

            try (MockedStatic<JwtUtil> mockedJwt = mockStatic(JwtUtil.class)) {
                mockedJwt.when(() -> JwtUtil.generateToken(any(Client.class))).thenReturn("mocked_token");
                mockedJwt.when(JwtUtil::getExpirationTime).thenReturn(LocalDateTime.now().plusDays(1));

                AuthResponse response = authService.login(request);

                assertNotNull(response);
                assertEquals("mocked_token", response.token());
                verify(authRepository).findByUsername("jdoe");
            }
        }

        @Test
        @DisplayName("Should throw RuntimeException when user not found")
        void shouldThrowWhenUserNotFound() {
            AuthRequest request = new AuthRequest("unknown", "password");
            when(authRepository.findByUsername("unknown")).thenReturn(Optional.empty());

            RuntimeException exception = assertThrows(RuntimeException.class, () -> authService.login(request));
            assertEquals("Client not found", exception.getMessage());
        }

        @Test
        @DisplayName("Should throw RuntimeException when password invalid")
        void shouldThrowWhenPasswordInvalid() {
            AuthRequest request = new AuthRequest("jdoe", "wrong_password");
            when(authRepository.findByUsername("jdoe")).thenReturn(Optional.of(clientEntity));

            RuntimeException exception = assertThrows(RuntimeException.class, () -> authService.login(request));
            assertEquals("Invalid credentials", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Tests for getUserFromToken")
    class GetUserFromTokenTests {
        @Test
        @DisplayName("Should get user from token")
        void shouldGetUserFromToken() {
            Token token = new Token("valid_token", LocalDateTime.now().plusDays(1));

            try (MockedStatic<JwtUtil> mockedJwt = mockStatic(JwtUtil.class)) {
                mockedJwt.when(() -> JwtUtil.validateToken("valid_token")).thenReturn(true);
                mockedJwt.when(() -> JwtUtil.extractClientId("valid_token")).thenReturn("12345678A");

                when(authRepository.findByDni("12345678A")).thenReturn(Optional.of(clientEntity));

                Client result = authService.getUserFromToken(token);

                assertNotNull(result);
                assertEquals("12345678A", result.getDni());
                verify(authRepository).findByDni("12345678A");
            }
        }

        @Test
        @DisplayName("Should throw exception when token invalid")
        void shouldThrowWhenTokenInvalid() {
            Token token = new Token("invalid_token", LocalDateTime.now().plusDays(1));

            try (MockedStatic<JwtUtil> mockedJwt = mockStatic(JwtUtil.class)) {
                mockedJwt.when(() -> JwtUtil.validateToken("invalid_token")).thenReturn(false);

                RuntimeException exception = assertThrows(RuntimeException.class,
                        () -> authService.getUserFromToken(token));
                assertEquals("Invalid or expired token", exception.getMessage());
            }
        }

        @Test
        @DisplayName("Should throw exception when client from token not found")
        void shouldThrowWhenClientFromTokenNotFound() {
            Token token = new Token("valid_token", LocalDateTime.now().plusDays(1));

            try (MockedStatic<JwtUtil> mockedJwt = mockStatic(JwtUtil.class)) {
                mockedJwt.when(() -> JwtUtil.validateToken("valid_token")).thenReturn(true);
                mockedJwt.when(() -> JwtUtil.extractClientId("valid_token")).thenReturn("12345678A");

                when(authRepository.findByDni("12345678A")).thenReturn(Optional.empty());

                RuntimeException exception = assertThrows(RuntimeException.class,
                        () -> authService.getUserFromToken(token));
                assertEquals("Client not found", exception.getMessage());
            }
        }
    }

    @Nested
    @DisplayName("Tests for generateTokenForUser")
    class GenerateTokenTests {
        @Test
        @DisplayName("Should generate token")
        void shouldGenerateToken() {
            try (MockedStatic<JwtUtil> mockedJwt = mockStatic(JwtUtil.class)) {
                String tokenString = "generated_token";
                LocalDateTime expiration = LocalDateTime.now().plusDays(1);

                mockedJwt.when(() -> JwtUtil.generateToken(client)).thenReturn(tokenString);
                mockedJwt.when(JwtUtil::getExpirationTime).thenReturn(expiration);

                Token result = authService.generateTokenForUser(client);

                assertNotNull(result);
                assertEquals(tokenString, result.getToken());
                assertEquals(expiration, result.getExpirationDate());
            }
        }
    }
}
