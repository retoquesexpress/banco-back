package com.fpmislata.banco_back.controller.webModel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpmislata.banco_back.domain.service.CreditCardService;
import com.fpmislata.banco_back.domain.service.dto.AccountDto;
import com.fpmislata.banco_back.domain.service.dto.CreditCardDto;
import com.fpmislata.banco_back.domain.service.dto.ClientDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CreditCardController.class)
class CreditCardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditCardService creditCardService;

    @MockBean
    private com.fpmislata.banco_back.domain.service.AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    private CreditCardDto creditCardDto;

    @BeforeEach
    void setUp() {
        ClientDto clientDto = new ClientDto("12345678A", "jdoe", "pass", "John", "Doe", "Smith", "token");
        AccountDto accountDto = new AccountDto("ES1234567890123456789012", 1000.0, clientDto, List.of(), List.of());
        creditCardDto = new CreditCardDto(1, "1234567890123456", java.time.LocalDate.now().plusYears(2), 123,
                "John Doe");
    }

    @Test
    @DisplayName("Should find all credit cards")
    void shouldFindAllCreditCards() throws Exception {
        when(creditCardService.findAll()).thenReturn(List.of(creditCardDto));

        mockMvc.perform(get("/api/credit-cards")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idCreditCard").value(1));
    }

    @Test
    @DisplayName("Should find credit card by ID")
    void shouldFindCreditCardById() throws Exception {
        when(creditCardService.findCreditCardById(1)).thenReturn(Optional.of(creditCardDto));

        mockMvc.perform(get("/api/credit-cards/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCreditCard").value(1));
    }
}
