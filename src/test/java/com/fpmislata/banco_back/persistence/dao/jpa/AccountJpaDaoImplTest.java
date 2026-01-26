package com.fpmislata.banco_back.persistence.dao.jpa;

import com.fpmislata.banco_back.persistence.TestConfig;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.AccountJpaEntity;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.ClientJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountJpaDaoImplTest {

    @Autowired
    private AccountJpaDao accountJpaDao;

    @Autowired 
    private ClientJpaDao clientJpaDao;

    @Test
    @DisplayName("Should find all accounts")
    void shouldFindAllAccounts() {
        List<AccountJpaEntity> accounts = accountJpaDao.findAll();
        assertFalse(accounts.isEmpty());
    }

    @Test
    @DisplayName("Should find account by IBAN")
    void shouldFindAccountByIban() {
        Optional<AccountJpaEntity> account = accountJpaDao.findByIban("ES1234567890123456789012");
        assertTrue(account.isPresent());
        assertEquals("ES1234567890123456789012", account.get().getIban());
    }

    @Test
    @DisplayName("Should find accounts by client")
    void shouldFindAccountsByClient() {
        Optional<ClientJpaEntity> client = clientJpaDao.findClientByDni("12345678A");
        assertTrue(client.isPresent());
        
        List<AccountJpaEntity> accounts = accountJpaDao.findByClient(client.get());
        assertFalse(accounts.isEmpty());
        assertEquals(client.get().getDni(), accounts.get(0).getClient().getDni());
    }

    @Test
    @DisplayName("Should save a new account")
    void shouldSaveAccount() {
         Optional<ClientJpaEntity> client = clientJpaDao.findClientByDni("12345678A");
         assertTrue(client.isPresent());

         AccountJpaEntity newAccount = new AccountJpaEntity("ES9999999999999999999999", 500.0, client.get(), null);
         AccountJpaEntity savedAccount = accountJpaDao.save(newAccount);

         assertNotNull(savedAccount);
         assertEquals("ES9999999999999999999999", savedAccount.getIban());
    }

    @Test
    @DisplayName("Should deposit money (update account)")
    void shouldDepositMoney() {
        String iban = "ES1234567890123456789012";
        Optional<AccountJpaEntity> accountOpt = accountJpaDao.findByIban(iban);
        assertTrue(accountOpt.isPresent());
        AccountJpaEntity account = accountOpt.get();
        
        Double initialBalance = account.getBalance();
        Double depositAmount = 100.0;
        account.setBalance(initialBalance + depositAmount);
        
        AccountJpaEntity updatedAccount = accountJpaDao.depositMoney(account, depositAmount, "Deposit Test");
        
        assertEquals(initialBalance + depositAmount, updatedAccount.getBalance());
    }

    @Test
    @DisplayName("Should withdraw money (update account)")
    void shouldWithdrawMoney() {
        String iban = "ES1234567890123456789012";
        Optional<AccountJpaEntity> accountOpt = accountJpaDao.findByIban(iban);
        assertTrue(accountOpt.isPresent());
        AccountJpaEntity account = accountOpt.get();
        
        Double initialBalance = account.getBalance();
        Double withdrawAmount = 50.0;
        account.setBalance(initialBalance - withdrawAmount);
        
        AccountJpaEntity updatedAccount = accountJpaDao.withdrawMoney(account, withdrawAmount, "Withdraw Test");
        
        assertEquals(initialBalance - withdrawAmount, updatedAccount.getBalance());
    }
}
