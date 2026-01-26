package com.fpmislata.banco_back.persistence.dao.jpa;

import com.fpmislata.banco_back.domain.service.dto.AccountDto;
import com.fpmislata.banco_back.persistence.TestConfig;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.AccountJpaEntity;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.CreditCardJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditCardJpaDaoImplTest {

    @Autowired
    private CreditCardJpaDao creditCardJpaDao;

    @Autowired
    private AccountJpaDao accountJpaDao;

    @Test
    @DisplayName("Should find all credit cards")
    void shouldFindAllCreditCards() {
        List<CreditCardJpaEntity> cards = creditCardJpaDao.findAll();
        assertFalse(cards.isEmpty());
    }

    @Test
    @DisplayName("Should find credit card by ID")
    void shouldFindCreditCardById() {
        Optional<CreditCardJpaEntity> card = creditCardJpaDao.findCreditCardById(1);
        assertTrue(card.isPresent());
        assertEquals(1, card.get().getIdCreditCard());
    }

    @Test
    @DisplayName("Should find credit cards by account")
    void shouldFindCreditCardsByAccount() {
        AccountDto accountDto = new AccountDto("ES1234567890123456789012", 0.0, null, null, null);
        List<CreditCardJpaEntity> cards = creditCardJpaDao.findAllCreditCardsByAccount(accountDto);
        assertFalse(cards.isEmpty());
    }
}
