package com.fpmislata.banco_back.persistence.repository;

import com.fpmislata.banco_back.domain.service.dto.ClientDto;
import com.fpmislata.banco_back.mapper.ClientMapper;
import com.fpmislata.banco_back.persistence.dao.jpa.ClientJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.ClientJpaEntity;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientRepositoryImplTest {

    @Mock
    private ClientJpaDao clientJpaDao;

    @InjectMocks
    private ClientRepositoryImpl clientRepository;

    private ClientJpaEntity clientJpaEntity;
    private ClientDto clientDto;

    @BeforeEach
    void setUp() {
        clientJpaEntity = new ClientJpaEntity("12345678A", "jdoe", "pass", "John", "Doe", "Smith", "tok");
        clientDto = new ClientDto("12345678A", "jdoe", "pass", "John", "Doe", "Smith", "tok");
    }

    @Nested
    @DisplayName("Tests for findAllClients")
    class FindAllTests {
        @Test
        @DisplayName("Should return all clients")
        void shouldReturnAllClients() {
            when(clientJpaDao.findAllClients()).thenReturn(List.of(clientJpaEntity));

            List<ClientDto> result = clientRepository.findAllClients();

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("jdoe", result.get(0).userName());
            verify(clientJpaDao).findAllClients();
        }
    }

    @Nested
    @DisplayName("Tests for find/get ClientByDni")
    class FindByDniTests {
        @Test
        @DisplayName("Should find client by DNI")
        void shouldFindClientByDni() {
            when(clientJpaDao.findClientByDni("12345678A")).thenReturn(Optional.of(clientJpaEntity));

            Optional<ClientDto> result = clientRepository.findClientByDni("12345678A");

            assertTrue(result.isPresent());
            assertEquals("12345678A", result.get().dni());
            verify(clientJpaDao).findClientByDni("12345678A");
        }

        @Test
        @DisplayName("Should get client by DNI")
        void shouldGetClientByDni() {
            when(clientJpaDao.findClientByDni("12345678A")).thenReturn(Optional.of(clientJpaEntity));

            Optional<ClientDto> result = clientRepository.getClientByDni("12345678A");

            assertTrue(result.isPresent());
             assertEquals("12345678A", result.get().dni());
            verify(clientJpaDao).findClientByDni("12345678A");
        }
    }

    @Nested
    @DisplayName("Tests for findClientByUserName")
    class FindByUserNameTests {
        @Test
        @DisplayName("Should find client by username")
        void shouldFindClientByUserName() {
            when(clientJpaDao.findClientByUserName("jdoe")).thenReturn(Optional.of(clientJpaEntity));

            Optional<ClientDto> result = clientRepository.findClientByUserName("jdoe");

            assertTrue(result.isPresent());
            assertEquals("jdoe", result.get().userName());
            verify(clientJpaDao).findClientByUserName("jdoe");
        }
    }

    @Nested
    @DisplayName("Tests for create/update/delete")
    class CrudTests {
        @Test
        @DisplayName("Should create client")
        void shouldCreateClient() {
            when(clientJpaDao.create(any(ClientJpaEntity.class))).thenReturn(clientJpaEntity);

            ClientDto result = clientRepository.create(clientDto);

            assertNotNull(result);
            assertEquals(clientDto.dni(), result.dni());
            verify(clientJpaDao).create(any(ClientJpaEntity.class));
        }

        @Test
        @DisplayName("Should update client")
        void shouldUpdateClient() {
            when(clientJpaDao.findClientByDni(clientDto.dni())).thenReturn(Optional.of(clientJpaEntity));
            when(clientJpaDao.update(any(ClientJpaEntity.class))).thenReturn(clientJpaEntity);

            ClientDto result = clientRepository.update(clientDto);

            assertNotNull(result);
            verify(clientJpaDao).update(any(ClientJpaEntity.class));
        }

        @Test
        @DisplayName("Should throw exception when updating if client not found")
        void shouldThrowExceptionWhenUpdatingIfNotFound() {
            when(clientJpaDao.findClientByDni(clientDto.dni())).thenReturn(Optional.empty());

            assertThrows(RuntimeException.class, () -> clientRepository.update(clientDto));
        }

        @Test
        @DisplayName("Should delete client")
        void shouldDeleteClient() {
            doNothing().when(clientJpaDao).delete("12345678A");

            clientRepository.delete("12345678A");

            verify(clientJpaDao).delete("12345678A");
        }
    }
}
