package com.fpmislata.banco_back.persistence;

import com.fpmislata.banco_back.persistence.dao.jpa.AccountJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.AccountMovementJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.ClientJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.CreditCardJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.impl.AccountJpaDaoImpl;
import com.fpmislata.banco_back.persistence.dao.jpa.impl.AccountMovementJpaDaoImpl;
import com.fpmislata.banco_back.persistence.dao.jpa.impl.ClientJpaDaoImpl;
import com.fpmislata.banco_back.persistence.dao.jpa.impl.CreditCardJpaDaoImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @Bean
    public ClientJpaDao clientDaoJpa() {
        return new ClientJpaDaoImpl();
    }

    @Bean
    public AccountJpaDao accountDaoJpa() {
        return new AccountJpaDaoImpl();
    }

    @Bean
    public AccountMovementJpaDao accountMovementDaoJpa() {
        return new AccountMovementJpaDaoImpl();
    }

    @Bean
    public CreditCardJpaDao creditCardDaoJpa() {
        return new CreditCardJpaDaoImpl();
    }
}
