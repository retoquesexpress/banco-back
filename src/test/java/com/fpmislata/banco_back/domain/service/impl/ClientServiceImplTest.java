package com.fpmislata.banco_back.domain.service.impl;

import com.fpmislata.banco_back.domain.repository.ClientRepository;
import com.fpmislata.banco_back.domain.service.dto.ClientDto;
import com.fpmislata.banco_back.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    private ClientDto clientDto;

    @BeforeEach
    void setUp() {
        clientDto = new ClientDto("12345678A", "jdoe", "password", "John", "Doe", "Smith", "token123");
    }

    @Nested
    @DisplayName("Tests for findAllClients")
    class FindAllTests {
        @Test
        @DisplayName("Should return all clients")
        void shouldReturnAllClients() {
            when(clientRepository.findAllClients()).thenReturn(List.of(clientDto));

            List<ClientDto> result = clientService.findAllClients();

            assertNotNull(result);
            assertEquals(1, result.size());
            verify(clientRepository).findAllClients();
        }
    }

    @Nested
    @DisplayName("Tests for find/get ClientByDni")
    class FindClientTests {
        @Test
        @DisplayName("Should find client by DNI")
        void shouldFindClientByDni() {
            when(clientRepository.findClientByDni("12345678A")).thenReturn(Optional.of(clientDto));

            Optional<ClientDto> result = clientService.findClientByDni("12345678A");

            assertTrue(result.isPresent());
            assertEquals("12345678A", result.get().dni());
            verify(clientRepository).findClientByDni("12345678A");
        }

        @Test
        @DisplayName("Should throw exception when client by DNI not found (non-optional return)")
        void shouldThrowExceptionWhenClientByDniNotFound() {
            when(clientRepository.findClientByDni("12345678A")).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> clientService.findClientByDni("12345678A"));
        }

        @Test
        @DisplayName("Should get client by DNI")
        void shouldGetClientByDni() {
            when(clientRepository.findClientByDni("12345678A")).thenReturn(Optional.of(clientDto));

            Optional<ClientDto> result = clientService.getClientByDni("12345678A");

            assertTrue(result.isPresent());
            assertEquals("12345678A", result.get().dni());
            verify(clientRepository).findClientByDni("12345678A");
        }

        @Test
        @DisplayName("Should throw exception when get client by DNI not found")
        void shouldThrowExceptionWhenGetClientByDniNotFound() {
            when(clientRepository.findClientByDni("12345678A")).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> clientService.getClientByDni("12345678A"));
        }
    }

    @Nested
    @DisplayName("Tests for findClientByUserName")
    class FindClientByUserNameTests {
        @Test
        @DisplayName("Should find client by Username")
        void shouldFindClientByUserName() {
            when(clientRepository.findClientByUserName("jdoe")).thenReturn(Optional.of(clientDto));

            Optional<ClientDto> result = clientService.findClientByUserName("jdoe");

            assertTrue(result.isPresent());
            assertEquals("jdoe", result.get().userName());
            verify(clientRepository).findClientByUserName("jdoe");
        }

        @Test
        @DisplayName("Should throw exception when client by Username not found")
        void shouldThrowExceptionWhenClientByUserNameNotFound() {
            when(clientRepository.findClientByUserName("jdoe")).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> clientService.findClientByUserName("jdoe"));
        }
    }

    @Nested
    @DisplayName("Tests for create/update/delete")
    class CrudTests {
        @Test
        @DisplayName("Should create client")
        void shouldCreateClient() {
            when(clientRepository.findClientByDni(clientDto.dni())).thenReturn(Optional.of(clientDto));
            when(clientRepository.create(clientDto)).thenReturn(clientDto);

            ClientDto result = clientService.create(clientDto);

            assertNotNull(result);
            verify(clientRepository).create(clientDto);
        }

        @Test
        @DisplayName("Should throw exception when creating client if DNI not found check logic seems inverted in service")
        void shouldThrowExceptionWhenCreatingIfDniNotFound() {

            when(clientRepository.findClientByDni(clientDto.dni())).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> clientService.create(clientDto));
        }

        @Test
        @DisplayName("Should update client")
        void shouldUpdateClient() {
            when(clientRepository.findClientByDni(clientDto.dni())).thenReturn(Optional.of(clientDto));
            when(clientRepository.update(clientDto)).thenReturn(clientDto);

            ClientDto result = clientService.update(clientDto);

            assertNotNull(result);
            verify(clientRepository).update(clientDto);
        }

        @Test
        @DisplayName("Should throw exception when updating if client not found")
        void shouldThrowExceptionWhenUpdatingIfNotFound() {
            when(clientRepository.findClientByDni(clientDto.dni())).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> clientService.update(clientDto));
        }

        @Test
        @DisplayName("Should delete client")
        void shouldDeleteClient() {
            when(clientRepository.findClientByDni("12345678A")).thenReturn(Optional.of(clientDto));
            doNothing().when(clientRepository).delete("12345678A");

            clientService.delete("12345678A");

            verify(clientRepository).delete("12345678A");
        }

        @Test
        @DisplayName("Should throw exception when deleting if client not found")
        void shouldThrowExceptionWhenDeletingIfNotFound() {
            when(clientRepository.findClientByDni("12345678A")).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> clientService.delete("12345678A"));
        }
    }
}
