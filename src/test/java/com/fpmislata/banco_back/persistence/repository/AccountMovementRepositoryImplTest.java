package com.fpmislata.banco_back.persistence.repository;

import com.fpmislata.banco_back.domain.model.enums.MovementType;
import com.fpmislata.banco_back.domain.model.enums.OriginMovement;
import com.fpmislata.banco_back.domain.repository.entity.AccountMovementEntity;
import com.fpmislata.banco_back.persistence.dao.jpa.AccountMovementJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.AccountMovementJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountMovementRepositoryImplTest {

    @Mock
    private AccountMovementJpaDao accountMovementJpaDao;

    @InjectMocks
    private AccountMovementRepositoryImpl accountMovementRepository;

    private AccountMovementJpaEntity accountMovementJpaEntity;

    @BeforeEach
    void setUp() {
         accountMovementJpaEntity = new AccountMovementJpaEntity(1, "1234", OriginMovement.DOMICILIACION, new Date(), 100.0, MovementType.RETIRAR, "Test Deposit");
    }

    @Nested
    @DisplayName("Tests for findAllAccountMovements")
    class FindAllTests {
        @Test
        @DisplayName("Should return all account movements")
        void shouldReturnAllAccountMovements() {
            when(accountMovementJpaDao.findAllAccountMovements()).thenReturn(List.of(accountMovementJpaEntity));

            List<AccountMovementEntity> result = accountMovementRepository.findAllAccountMovements();

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(1, result.get(0).idAccountMovent());
            verify(accountMovementJpaDao).findAllAccountMovements();
        }
    }

    @Nested
    @DisplayName("Tests for get/find AccountMovementById")
    class FindByIdTests {
        @Test
        @DisplayName("Should get account movement by ID (orElse null)")
        void shouldGetAccountMovementById() {
            when(accountMovementJpaDao.getAccountMovementById(1)).thenReturn(Optional.of(accountMovementJpaEntity));

            AccountMovementEntity result = accountMovementRepository.getAccountMovementById(1);

            assertNotNull(result);
            assertEquals(1, result.idAccountMovent());
            verify(accountMovementJpaDao).getAccountMovementById(1);
        }

        @Test
        @DisplayName("Should return null when get account movement by ID not found")
        void shouldReturnNullWhenNotFound() {
            when(accountMovementJpaDao.getAccountMovementById(1)).thenReturn(Optional.empty());

            AccountMovementEntity result = accountMovementRepository.getAccountMovementById(1);

            assertNull(result);
        }

        @Test
        @DisplayName("Should find account movement by ID (Optional)")
        void shouldFindAccountMovementById() {
            when(accountMovementJpaDao.findAccountMovementById(1)).thenReturn(Optional.of(accountMovementJpaEntity));

            Optional<AccountMovementEntity> result = accountMovementRepository.findAccountMovementById(1);

            assertTrue(result.isPresent());
             assertEquals(1, result.get().idAccountMovent());
            verify(accountMovementJpaDao).findAccountMovementById(1);
        }
    }

    @Nested
    @DisplayName("Tests for findByCreditCardOrigin")
    class FindByCreditCardOriginTests {
        @Test
        @DisplayName("Should find movements by credit card origin")
        void shouldFindByCreditCardOrigin() {
            when(accountMovementJpaDao.findByCreditCardOrigin("1234")).thenReturn(List.of(accountMovementJpaEntity));

            List<AccountMovementEntity> result = accountMovementRepository.findByCreditCardOrigin("1234");

            assertNotNull(result);
            assertEquals(1, result.size());
            verify(accountMovementJpaDao).findByCreditCardOrigin("1234");
        }
    }
}
