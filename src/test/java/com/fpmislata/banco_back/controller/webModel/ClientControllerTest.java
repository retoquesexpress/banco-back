package com.fpmislata.banco_back.controller.webModel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpmislata.banco_back.controller.webModel.request.ClientInsertRequest;
import com.fpmislata.banco_back.domain.service.ClientService;
import com.fpmislata.banco_back.domain.service.dto.ClientDto;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;

    private ClientDto clientDto;
    private ClientInsertRequest clientRequest;

    @BeforeEach
    void setUp() {
        clientDto = new ClientDto("12345678A", "jdoe", "pass", "John", "Doe", "Smith", "token");
        clientRequest = new ClientInsertRequest("12345678A", "jdoe", "pass", "John", "Doe", "Smith", "token");
    }

    @Nested
    @DisplayName("Tests for CRUD operations")
    class CRUDTests {

        @Test
        @DisplayName("Should find all clients")
        void shouldFindAllClients() throws Exception {
            when(clientService.findAllClients()).thenReturn(List.of(clientDto));

            mockMvc.perform(get("/api/clients")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].dni").value("12345678A"));
        }

        @Test
        @DisplayName("Should get client by DNI")
        void shouldGetClientByDni() throws Exception {
            when(clientService.getClientByDni("12345678A")).thenReturn(Optional.of(clientDto));

            mockMvc.perform(get("/api/clients/12345678A")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.dni").value("12345678A"));
        }

        @Test
        @DisplayName("Should create client")
        void shouldCreateClient() throws Exception {
            when(clientService.create(any(ClientDto.class))).thenReturn(clientDto);

            mockMvc.perform(post("/api/clients")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(clientRequest)))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.dni").value("12345678A"));
        }

        @Test
        @DisplayName("Should update client")
        void shouldUpdateClient() throws Exception {
            when(clientService.update(any(ClientDto.class))).thenReturn(clientDto);

            mockMvc.perform(put("/api/clients/12345678A")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(clientRequest)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.dni").value("12345678A"));
        }

        @Test
        @DisplayName("Should delete client")
        void shouldDeleteClient() throws Exception {
            doNothing().when(clientService).delete("12345678A");

            mockMvc.perform(delete("/api/clients/12345678A"))
                    .andExpect(status().isNoContent());
        }
    }
}
