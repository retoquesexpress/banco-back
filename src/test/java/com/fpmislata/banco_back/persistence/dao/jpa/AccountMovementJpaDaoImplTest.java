package com.fpmislata.banco_back.persistence.dao.jpa;

import com.fpmislata.banco_back.persistence.TestConfig;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.AccountMovementJpaEntity;
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
class AccountMovementJpaDaoImplTest {

    @Autowired
    private AccountMovementJpaDao accountMovementJpaDao;

    @Test
    @DisplayName("Should find all account movements")
    void shouldFindAllAccountMovements() {
        List<AccountMovementJpaEntity> movements = accountMovementJpaDao.findAllAccountMovements();
        assertFalse(movements.isEmpty());
    }

    @Test
    @DisplayName("Should find account movement by ID")
    void shouldFindAccountMovementById() {
        Optional<AccountMovementJpaEntity> movement = accountMovementJpaDao.findAccountMovementById(1);
        assertTrue(movement.isPresent());
        assertEquals(1, movement.get().getIdAccountMovement());
    }

    @Test
    @DisplayName("Should get account movement by ID")
    void shouldGetAccountMovementById() {
        Optional<AccountMovementJpaEntity> movement = accountMovementJpaDao.getAccountMovementById(1);
        assertTrue(movement.isPresent());
        assertEquals(1, movement.get().getIdAccountMovement());
    }

    @Test
    @DisplayName("Should find movements by credit card origin")
    void shouldFindByCreditCardOrigin() {
        List<AccountMovementJpaEntity> movements = accountMovementJpaDao.findByCreditCardOrigin("1234567890123456");
        assertFalse(movements.isEmpty());
    }
}
