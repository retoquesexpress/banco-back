package com.fpmislata.banco_back.controller.webModel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpmislata.banco_back.domain.service.AccountService;
import com.fpmislata.banco_back.domain.service.ClientService;
import com.fpmislata.banco_back.domain.service.dto.AccountDto;
import com.fpmislata.banco_back.domain.service.dto.ClientDto;
import com.fpmislata.banco_back.domain.service.dto.PagoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;
    @MockBean
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;

    private AccountDto accountDto;
    private ClientDto clientDto;

    @BeforeEach
    void setUp() {
        clientDto = new ClientDto("12345678A", "jdoe", "pass", "John", "Doe", "Smith", "token");
        accountDto = new AccountDto("ES1234567890123456789012", 1000.0, clientDto, List.of(), List.of());
    }

    @Nested
    @DisplayName("Tests for GET operations")
    class GetTests {
        @Test
        @DisplayName("Should return account by IBAN")
        void shouldGetAccountByIban() throws Exception {
            when(accountService.getByIban("ES1234567890123456789012")).thenReturn(accountDto);

            mockMvc.perform(get("/api/accounts/ES1234567890123456789012")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.iban").value("ES1234567890123456789012"));
        }

        @Test
        @DisplayName("Should find accounts by client DNI")
        void shouldFindAccountsByClient() throws Exception {
            when(clientService.getClientByDni("12345678A")).thenReturn(Optional.of(clientDto));
            when(accountService.findByClient(any())).thenReturn(List.of(accountDto));

            mockMvc.perform(get("/api/accounts")
                    .param("dni", "12345678A")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].iban").value("ES1234567890123456789012"));
        }
    }

    @Nested
    @DisplayName("Tests for Banking Operations")
    class BankingOperationsTests {
        @Test
        @DisplayName("Should deposit money")
        void shouldDepositMoney() throws Exception {
            PagoDto pagoDto = new PagoDto(100.0, "Deposit Test");
            when(accountService.getByIban("ES1234567890123456789012")).thenReturn(accountDto);
            when(accountService.depositMoney(any(), eq(100.0), eq("Deposit Test"), isNull()))
                    .thenReturn(accountDto);

            mockMvc.perform(post("/api/accounts/ES1234567890123456789012/deposit")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(pagoDto)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.iban").value("ES1234567890123456789012"));
        }

        @Test
        @DisplayName("Should withdraw money")
        void shouldWithdrawMoney() throws Exception {
            PagoDto pagoDto = new PagoDto(50.0, "Withdraw Test");
            when(accountService.getByIban("ES1234567890123456789012")).thenReturn(accountDto);
            when(accountService.withdrawMoney(any(), eq(50.0), eq("Withdraw Test"), isNull()))
                    .thenReturn(accountDto);

            mockMvc.perform(post("/api/accounts/ES1234567890123456789012/withdraw")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(pagoDto)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.iban").value("ES1234567890123456789012"));
        }
    }
}
