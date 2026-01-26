package com.fpmislata.banco_back.persistence.repository;

import com.fpmislata.banco_back.domain.repository.entity.ClientEntity;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthRepositoryImplTest {

    @Mock
    private ClientJpaDao clientJpaDao;

    @InjectMocks
    private AuthRepositoryImpl authRepository;

    private ClientJpaEntity clientJpaEntity;
    private ClientEntity clientEntity;

    @BeforeEach
    void setUp() {
        clientJpaEntity = new ClientJpaEntity("12345678A", "jdoe", "pass", "John", "Doe", "Smith", "tok");
        clientEntity = new ClientEntity("12345678A", "jdoe", "pass", "John", "Doe", "Smith", "tok");
    }

    @Nested
    @DisplayName("Tests for findByUsername")
    class FindByUsernameTests {
        @Test
        @DisplayName("Should find client by username")
        void shouldFindClientByUsername() {
            when(clientJpaDao.findClientByUserName("jdoe")).thenReturn(Optional.of(clientJpaEntity));

            Optional<ClientEntity> result = authRepository.findByUsername("jdoe");

            assertTrue(result.isPresent());
            assertEquals("jdoe", result.get().userName());
            verify(clientJpaDao).findClientByUserName("jdoe");
        }
    }

    @Nested
    @DisplayName("Tests for findByDni")
    class FindByDniTests {
        @Test
        @DisplayName("Should find client by DNI")
        void shouldFindClientByDni() {
            when(clientJpaDao.findClientByDni("12345678A")).thenReturn(Optional.of(clientJpaEntity));

            Optional<ClientEntity> result = authRepository.findByDni("12345678A");

            assertTrue(result.isPresent());
            assertEquals("12345678A", result.get().dni());
            verify(clientJpaDao).findClientByDni("12345678A");
        }
    }

    @Nested
    @DisplayName("Tests for register")
    class RegisterTests {
        @Test
        @DisplayName("Should register client")
        void shouldRegisterClient() {
            when(clientJpaDao.create(any(ClientJpaEntity.class))).thenReturn(clientJpaEntity);

            ClientEntity result = authRepository.register(clientEntity);

            assertNotNull(result);
            assertEquals("12345678A", result.dni());
            verify(clientJpaDao).create(any(ClientJpaEntity.class));
        }
    }

    @Nested
    @DisplayName("Tests for existsByUsername")
    class ExistsByUsernameTests {
        @Test
        @DisplayName("Should return true if username exists")
        void shouldReturnTrueIfExists() {
            when(clientJpaDao.findClientByUserName("jdoe")).thenReturn(Optional.of(clientJpaEntity));

            boolean result = authRepository.existsByUsername("jdoe");

            assertTrue(result);
            verify(clientJpaDao).findClientByUserName("jdoe");
        }

        @Test
        @DisplayName("Should return false if username does not exist")
        void shouldReturnFalseIfNotExists() {
            when(clientJpaDao.findClientByUserName("jdoe")).thenReturn(Optional.empty());

            boolean result = authRepository.existsByUsername("jdoe");

            assertFalse(result);
        }
    }
}
