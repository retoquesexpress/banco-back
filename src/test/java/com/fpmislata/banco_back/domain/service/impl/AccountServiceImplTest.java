package com.fpmislata.banco_back.domain.service.impl;

import com.fpmislata.banco_back.domain.model.Client;
import com.fpmislata.banco_back.domain.repository.AccountRepository;
import com.fpmislata.banco_back.domain.repository.entity.AccountEntity;
import com.fpmislata.banco_back.domain.repository.entity.ClientEntity;
import com.fpmislata.banco_back.domain.service.dto.AccountDto;
import com.fpmislata.banco_back.domain.service.dto.ClientDto;
import com.fpmislata.banco_back.exception.BusinessException;
import com.fpmislata.banco_back.mapper.AccountMapper;
import com.fpmislata.banco_back.mapper.ClientMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private AccountDto accountDto;
    private ClientDto clientDto;
    private AccountEntity accountEntity;
    private Client client;

    @BeforeEach
    void setUp() {
        clientDto = new ClientDto("12345678A", "jdoe", "password", "John", "Doe", "Smith", "token123");
        client = new Client("12345678A", "jdoe", "password", "John", "Doe", "Smith", "token123");

        accountDto = new AccountDto("ES1234567890123456789012", 1000.00, clientDto, Collections.emptyList(),
                Collections.emptyList());
        accountEntity = AccountMapper.getInstance().fromAccountDtoToAccountEntity(accountDto);
    }

    @Nested
    @DisplayName("Tests for findAll")
    class FindAllTests {
        @Test
        @DisplayName("Should return all accounts")
        void shouldFindAll() {
            when(accountRepository.findAll()).thenReturn(List.of(accountEntity));
            List<AccountDto> result = accountService.findAll();
            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertEquals(1, result.size());
            verify(accountRepository).findAll();
        }
    }

    @Nested
    @DisplayName("Tests for findByClient")
    class FindByClientTests {
        @Test
        @DisplayName("Should return accounts for a client")
        void shouldFindByClient() {
            when(accountRepository.findByClient(any(ClientEntity.class))).thenReturn(List.of(accountEntity));

            List<AccountDto> result = accountService.findByClient(client);

            assertNotNull(result);
            assertFalse(result.isEmpty());
            assertEquals(1, result.size());
            verify(accountRepository).findByClient(any(ClientEntity.class));
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when client is null")
        void shouldThrowExceptionWhenClientIsNull() {
            assertThrows(IllegalArgumentException.class, () -> accountService.findByClient(null));
        }
    }

    @Nested
    @DisplayName("Tests for getByIban and findByIban")
    class FindByIbanTests {
        @Test
        @DisplayName("Should find account by IBAN (getByIban)")
        void shouldGetByIban() {
            when(accountRepository.findByIban("ES123")).thenReturn(Optional.of(accountEntity));
            AccountDto result = accountService.getByIban("ES123");
            assertNotNull(result);
            assertEquals("ES1234567890123456789012", result.iban());
            verify(accountRepository).findByIban("ES123");
        }

        @Test
        @DisplayName("Should find account by IBAN (findByIban)")
        void shouldFindByIban() {
            when(accountRepository.findByIban("ES123")).thenReturn(Optional.of(accountEntity));
            Optional<AccountDto> result = accountService.findByIban("ES123");
            assertTrue(result.isPresent());
            assertEquals("ES1234567890123456789012", result.get().iban());
            verify(accountRepository).findByIban("ES123");
        }
    }

    @Nested
    @DisplayName("Tests for deposit and withdraw")
    class TransactionTests {
        @Test
        @DisplayName("Should deposit money")
        void shouldDepositMoney() {
            Double amount = 100.00;
            String concept = "Deposit test";
            String cardNumber = "1234567890123456";

            when(accountRepository.depositMoney(any(AccountEntity.class), eq(amount), eq(concept), eq(cardNumber)))
                    .thenReturn(accountEntity);

            AccountDto result = accountService.depositMoney(accountDto, amount, concept, cardNumber);

            assertNotNull(result);
            verify(accountRepository).depositMoney(any(AccountEntity.class), eq(amount), eq(concept), eq(cardNumber));
        }

        @Test
        @DisplayName("Should withdraw money")
        void shouldWithdrawMoney() {
            Double amount = 50.00;
            String concept = "Withdraw test";
            String cardNumber = "1234567890123456";

            when(accountRepository.withdrawMoney(any(AccountEntity.class), eq(amount), eq(concept), eq(cardNumber)))
                    .thenReturn(accountEntity);

            AccountDto result = accountService.withdrawMoney(accountDto, amount, concept, cardNumber);

            assertNotNull(result);
            verify(accountRepository).withdrawMoney(any(AccountEntity.class), eq(amount), eq(concept), eq(cardNumber));
        }

        @Test
        @DisplayName("Should throw BusinessException when concept is too short")
        void shouldThrowExceptionWhenConceptTooShort() {
            Double amount = 50.00;
            String concept = "ab";

            assertThrows(BusinessException.class,
                    () -> accountService.depositMoney(accountDto, amount, concept, "1234567890123456"));
        }
    }
}
