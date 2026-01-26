package com.fpmislata.banco_back.domain.service.impl;

import com.fpmislata.banco_back.domain.model.enums.MovementType;
import com.fpmislata.banco_back.domain.model.enums.OriginMovement;
import com.fpmislata.banco_back.domain.repository.AccountMovementRepository;
import com.fpmislata.banco_back.domain.repository.entity.AccountMovementEntity;
import com.fpmislata.banco_back.domain.service.dto.AccountMovementDto;
import com.fpmislata.banco_back.mapper.AccountMovementMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountMovementServiceImplTest {

    @Mock
    private AccountMovementRepository accountMovementRepository;

    @InjectMocks
    private AccountMovementServiceImpl accountMovementService;

    private AccountMovementDto accountMovementDto;
    private AccountMovementEntity accountMovementEntity;

    @BeforeEach
    void setUp() {
        accountMovementDto = new AccountMovementDto(1, "1234", OriginMovement.DOMICILIACION, new Date(), 100.0,
                MovementType.DEPOSITAR, "Test Deposit");
        accountMovementEntity = new AccountMovementEntity(1, "1234", OriginMovement.DOMICILIACION, new Date(), 100.0,
                MovementType.DEPOSITAR, "Test Deposit");
    }

    @Nested
    @DisplayName("Tests for findAllAccountMovements")
    class FindAllTests {
        @Test
        @DisplayName("Should return all account movements")
        void shouldReturnAllAccountMovements() {
            when(accountMovementRepository.findAllAccountMovements()).thenReturn(List.of(accountMovementEntity));

            List<AccountMovementDto> result = accountMovementService.findAllAccountMovements();

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(accountMovementEntity.idAccountMovent(), result.get(0).idAccountMovement());
            verify(accountMovementRepository, times(2)).findAllAccountMovements();
        }

        @Test
        @DisplayName("Should throw exception when no movements found")
        void shouldThrowExceptionWhenNoMovements() {
            when(accountMovementRepository.findAllAccountMovements()).thenReturn(Collections.emptyList());

            RuntimeException exception = assertThrows(RuntimeException.class,
                    () -> accountMovementService.findAllAccountMovements());
            assertEquals("No account movements found", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("Tests for find/get AccountMovementById")
    class FindByIdTests {
        @Test
        @DisplayName("Should get account movement by ID")
        void shouldGetAccountMovementById() {
            when(accountMovementRepository.findAccountMovementById(1)).thenReturn(Optional.of(accountMovementEntity));

            AccountMovementDto result = accountMovementService.getAccountMovementById(1);

            assertNotNull(result);
            assertEquals(1, result.idAccountMovement());
            verify(accountMovementRepository).findAccountMovementById(1);
        }

        @Test
        @DisplayName("Should throw exception when movement by ID not found (get)")
        void shouldThrowExceptionWhenGetNotFound() {
            when(accountMovementRepository.findAccountMovementById(1)).thenReturn(Optional.empty());

            RuntimeException exception = assertThrows(RuntimeException.class,
                    () -> accountMovementService.getAccountMovementById(1));
            assertEquals("Account movement not found with id: 1", exception.getMessage());
        }

        @Test
        @DisplayName("Should find account movement by ID (optional)")
        void shouldFindAccountMovementById() {
            when(accountMovementRepository.findAccountMovementById(1)).thenReturn(Optional.of(accountMovementEntity));

            Optional<AccountMovementDto> result = accountMovementService.findAccountMovementById(1);

            assertTrue(result.isPresent());
            assertEquals(1, result.get().idAccountMovement());
            verify(accountMovementRepository).findAccountMovementById(1);
        }
    }
}
