package com.fpmislata.banco_back.controller.webModel;

import com.fpmislata.banco_back.domain.model.enums.MovementType;
import com.fpmislata.banco_back.domain.model.enums.OriginMovement;
import com.fpmislata.banco_back.domain.service.AccountMovementService;
import com.fpmislata.banco_back.domain.service.dto.AccountMovementDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountMovementController.class)
class AccountMovementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountMovementService accountMovementService;

    private AccountMovementDto accountMovementDto;

    @BeforeEach
    void setUp() {
        accountMovementDto = new AccountMovementDto(1, "1234", OriginMovement.DOMICILIACION, new Date(), 100.0,
                MovementType.DEPOSITAR, "Test");
    }

    @Test
    @DisplayName("Should return all account movements")
    void shouldReturnAllAccountMovements() throws Exception {
        when(accountMovementService.findAllAccountMovements()).thenReturn(List.of(accountMovementDto));

        mockMvc.perform(get("/api/movements")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idAccountMovement").value(1))
                .andExpect(jsonPath("$[0].amount").value(100.0))
                .andExpect(jsonPath("$[0].concept").value("Test"))
                .andExpect(jsonPath("$[0].movementType").value("DEPOSITAR"));
    }

    @Test
    @DisplayName("Should return account movement by ID")
    void shouldReturnAccountMovementById() throws Exception {
        when(accountMovementService.findAccountMovementById(1)).thenReturn(Optional.of(accountMovementDto));

        mockMvc.perform(get("/api/movements/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idAccountMovement").value(1))
                .andExpect(jsonPath("$.amount").value(100.0))
                .andExpect(jsonPath("$.concept").value("Test"));
    }

    @Test
    @DisplayName("Should return 404 when movement not found")
    void shouldReturnNotFoundWhenMovementMissing() throws Exception {
        when(accountMovementService.findAccountMovementById(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/movements/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
