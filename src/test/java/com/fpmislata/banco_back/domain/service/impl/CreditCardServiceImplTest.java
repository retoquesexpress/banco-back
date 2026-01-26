package com.fpmislata.banco_back.domain.service.impl;

import com.fpmislata.banco_back.domain.repository.CreditCardRepository;
import com.fpmislata.banco_back.domain.service.dto.AccountDto;
import com.fpmislata.banco_back.domain.service.dto.ClientDto;
import com.fpmislata.banco_back.domain.service.dto.CreditCardDto;
import com.fpmislata.banco_back.exception.ResourceNotFoundException;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditCardServiceImplTest {

    @Mock
    private CreditCardRepository creditCardRepository;

    @InjectMocks
    private CreditCardServiceImpl creditCardService;

    private CreditCardDto creditCardDto;
    private AccountDto accountDto;

    @BeforeEach
    void setUp() {
        ClientDto clientDto = new ClientDto("12345678A", "jdoe", "pass", "John", "Doe", "Smith", "token123");
        accountDto = new AccountDto("ES1234567890123456789012", 1000.00, clientDto, Collections.emptyList(),
                Collections.emptyList());
        creditCardDto = new CreditCardDto(1, "1234567890123456", java.time.LocalDate.now().plusYears(2), 123,
                "Jose Smith");
    }

    @Nested
    @DisplayName("Tests for findAllCreditCardsByAccount")
    class FindAllByAccountTests {
        @Test
        @DisplayName("Should return credit cards for account")
        void shouldReturnCreditCardsByAccount() {
            when(creditCardRepository.findAllCreditCardsByAccount(accountDto)).thenReturn(List.of(creditCardDto));

            List<CreditCardDto> result = creditCardService.findAllCreditCardsByAccount(accountDto);

            assertNotNull(result);
            assertEquals(1, result.size());
            verify(creditCardRepository).findAllCreditCardsByAccount(accountDto);
        }

        @Test
        @DisplayName("Should throw exception when no credit cards found")
        void shouldThrowExceptionWhenNoCreditCards() {
            when(creditCardRepository.findAllCreditCardsByAccount(accountDto)).thenReturn(Collections.emptyList());

            assertThrows(ResourceNotFoundException.class,
                    () -> creditCardService.findAllCreditCardsByAccount(accountDto));
        }
    }

    @Nested
    @DisplayName("Tests for findCreditCardById")
    class FindByIdTests {
        @Test
        @DisplayName("Should find credit card by ID")
        void shouldFindCreditCardById() {
            when(creditCardRepository.findCreditCardById(1)).thenReturn(Optional.of(creditCardDto));

            Optional<CreditCardDto> result = creditCardService.findCreditCardById(1);

            assertTrue(result.isPresent());
            assertEquals(1, result.get().idCreditCard());
            verify(creditCardRepository).findCreditCardById(1);
        }

        @Test
        @DisplayName("Should throw exception when credit card not found")
        void shouldThrowExceptionWhenNotFound() {
            when(creditCardRepository.findCreditCardById(1)).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> creditCardService.findCreditCardById(1));
        }
    }

    @Nested
    @DisplayName("Tests for findAll")
    class FindAllTests {
        @Test
        @DisplayName("Should return all credit cards")
        void shouldReturnAllCreditCards() {
            when(creditCardRepository.findAll()).thenReturn(List.of(creditCardDto));

            List<CreditCardDto> result = creditCardService.findAll();

            assertNotNull(result);
            assertEquals(1, result.size());
            verify(creditCardRepository).findAll();
        }
    }
}
