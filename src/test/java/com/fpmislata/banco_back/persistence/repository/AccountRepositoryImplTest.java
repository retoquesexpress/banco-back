package com.fpmislata.banco_back.persistence.repository;

import com.fpmislata.banco_back.domain.model.enums.MovementType;
import com.fpmislata.banco_back.domain.repository.entity.AccountEntity;
import com.fpmislata.banco_back.domain.repository.entity.ClientEntity;
import com.fpmislata.banco_back.persistence.dao.jpa.AccountJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.AccountMovementJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.AccountJpaEntity;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.ClientJpaEntity;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.CreditCardJpaEntity;
import jakarta.persistence.Column;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountRepositoryImplTest {

    @Mock
    private AccountJpaDao accountJpaDao;

    @Mock
    private AccountMovementJpaDao accountMovementJpaDao;

    @InjectMocks
    private AccountRepositoryImpl accountRepository;

    private AccountJpaEntity accountJpaEntity;
    private ClientJpaEntity clientJpaEntity;
    private ClientEntity clientEntity;

    @BeforeEach
    void setUp() {
        clientJpaEntity = new ClientJpaEntity("46786589a", "jdoe", "pass", "John", "Doe", "Smith", "tok");
        clientEntity = new ClientEntity("12345678A", "jdoe", "pass", "John", "Doe", "Smith", "tok");

        accountJpaEntity = new AccountJpaEntity("ES1234567890123456789012", 1000.00, clientJpaEntity, List.of());
        accountJpaEntity.setCreditCards(Collections.emptyList());
    }

    @Nested
    @DisplayName("Tests for findAll")
    class FindAllTests {
        @Test
        @DisplayName("Should return all accounts")
        void shouldReturnAllAccounts() {
            when(accountJpaDao.findAll()).thenReturn(List.of(accountJpaEntity));

            List<AccountEntity> result = accountRepository.findAll();

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(accountJpaEntity.getIban(), result.get(0).iban());
            verify(accountJpaDao).findAll();
        }
    }

    @Nested
    @DisplayName("Tests for findByClient")
    class FindByClientTests {
        @Test
        @DisplayName("Should return accounts by client")
        void shouldReturnAccountsByClient() {
            when(accountJpaDao.findByClient(any(ClientJpaEntity.class))).thenReturn(List.of(accountJpaEntity));

            List<AccountEntity> result = accountRepository.findByClient(clientEntity);

            assertNotNull(result);
            assertEquals(1, result.size());
            verify(accountJpaDao).findByClient(any(ClientJpaEntity.class));
        }
    }

    @Nested
    @DisplayName("Tests for getByIban")
    class GetByIbanTests {
        @Test
        @DisplayName("Should return account by IBAN")
        void shouldReturnAccountByIban() {
            when(accountJpaDao.findByIban("ES123")).thenReturn(Optional.of(accountJpaEntity));

            AccountEntity result = accountRepository.getByIban("ES123");

            assertNotNull(result);
            assertEquals("ES1234567890123456789012", result.iban());
            verify(accountJpaDao).findByIban("ES123");
        }

        @Test
        @DisplayName("Should return null when IBAN not found")
        void shouldReturnNullWhenNotFound() {
            when(accountJpaDao.findByIban("ES123")).thenReturn(Optional.empty());

            AccountEntity result = accountRepository.getByIban("ES123");

            assertNull(result);
        }
    }

    @Nested
    @DisplayName("Tests for findByIban")
    class FindByIbanTests {
        @Test
        @DisplayName("Should return Optional account by IBAN")
        void shouldReturnOptionalAccountByIban() {
            when(accountJpaDao.findByIban("ES123")).thenReturn(Optional.of(accountJpaEntity));

            Optional<AccountEntity> result = accountRepository.findByIban("ES123");

            assertTrue(result.isPresent());
            assertEquals("ES1234567890123456789012", result.get().iban());
        }
    }

    @Nested
    @DisplayName("Tests for deposit/withdraw")
    class TransactionTests {
        @Test
        @DisplayName("Should deposit money")
        void shouldDepositMoney() {
            Double amount = 100.0;
            String concept = "Deposit";

            AccountEntity accountEntityInput = new AccountEntity(
                    "ES1234567890123456789012",
                    1000.00,
                    clientEntity,
                    Collections.emptyList(),
                    Collections.emptyList());

            when(accountJpaDao.save(any(AccountJpaEntity.class))).thenReturn(accountJpaEntity);

            AccountEntity result = accountRepository.depositMoney(accountEntityInput, amount, concept,
                    "1234567890123456");

            assertNotNull(result);
            verify(accountJpaDao).save(any(AccountJpaEntity.class));
        }

        @Test
        @DisplayName("Should withdraw money")
        void shouldWithdrawMoney() {
            Double amount = 50.0;
            String concept = "Withdraw";
            AccountEntity accountEntityInput = new AccountEntity(
                    "ES1234567890123456789012",
                    1000.00,
                    clientEntity,
                    Collections.emptyList(),
                    Collections.emptyList());

            when(accountJpaDao.save(any(AccountJpaEntity.class))).thenReturn(accountJpaEntity);

            AccountEntity result = accountRepository.withdrawMoney(accountEntityInput, amount, concept,
                    "1234567890123456");

            assertNotNull(result);
            verify(accountJpaDao).save(any(AccountJpaEntity.class));
        }
    }
}
