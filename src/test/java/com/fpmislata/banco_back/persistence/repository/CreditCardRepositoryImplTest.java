package com.fpmislata.banco_back.persistence.repository;

import com.fpmislata.banco_back.domain.service.dto.AccountDto;
import com.fpmislata.banco_back.domain.service.dto.ClientDto;
import com.fpmislata.banco_back.domain.service.dto.CreditCardDto;
import com.fpmislata.banco_back.persistence.dao.jpa.CreditCardJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.AccountJpaEntity;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.ClientJpaEntity;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.CreditCardJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditCardRepositoryImplTest {

    @Mock
    private CreditCardJpaDao creditCardJpaDao;

    @InjectMocks
    private CreditCardRepositoryImpl creditCardRepository;

    private CreditCardJpaEntity creditCardJpaEntity;
    private AccountDto accountDto;
    private AccountJpaEntity accountJpaEntity;
    private ClientJpaEntity clientJpaEntity;
    private ClientDto clientDto;

    @BeforeEach
    void setUp() {
        clientJpaEntity = new ClientJpaEntity("12345678A", "jdoe", "pass", "John", "Doe", "Smith", "tok");
        clientDto = new ClientDto("12345678A", "jdoe", "pass", "John", "Doe", "Smith", "tok");

        accountJpaEntity = new AccountJpaEntity("ES1234567890123456789012", 1000.00, clientJpaEntity,
                Collections.emptyList());
        accountDto = new AccountDto("ES1234567890123456789012", 1000.00, clientDto, Collections.emptyList(),
                Collections.emptyList());

        creditCardJpaEntity = new CreditCardJpaEntity(1, "1234567890123456", java.time.LocalDate.now().plusYears(2),
                123, "Violet Sorrengail", accountJpaEntity);
    }

    @Nested
    @DisplayName("Tests for findAllCreditCardsByAccount")
    class FindAllByAccountTests {
        @Test
        @DisplayName("Should return credit cards by account")
        void shouldReturnCreditCardsByAccount() {
            when(creditCardJpaDao.findAllCreditCardsByAccount(accountDto)).thenReturn(List.of(creditCardJpaEntity));

            List<CreditCardDto> result = creditCardRepository.findAllCreditCardsByAccount(accountDto);

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals("1234567890123456", result.get(0).cardNumber());
            verify(creditCardJpaDao).findAllCreditCardsByAccount(accountDto);
        }
    }

    @Nested
    @DisplayName("Tests for findCreditCardById")
    class FindByIdTests {
        @Test
        @DisplayName("Should find credit card by ID")
        void shouldFindCreditCardById() {
            when(creditCardJpaDao.findCreditCardById(1)).thenReturn(Optional.of(creditCardJpaEntity));

            Optional<CreditCardDto> result = creditCardRepository.findCreditCardById(1);

            assertTrue(result.isPresent());
            assertEquals(1, result.get().idCreditCard());
            verify(creditCardJpaDao).findCreditCardById(1);
        }
    }

    @Nested
    @DisplayName("Tests for findAll")
    class FindAllTests {
        @Test
        @DisplayName("Should return all credit cards")
        void shouldReturnAllCreditCards() {
            when(creditCardJpaDao.findAll()).thenReturn(List.of(creditCardJpaEntity));

            List<CreditCardDto> result = creditCardRepository.findAll();

            assertNotNull(result);
            assertEquals(1, result.size());
            verify(creditCardJpaDao).findAll();
        }
    }
}
