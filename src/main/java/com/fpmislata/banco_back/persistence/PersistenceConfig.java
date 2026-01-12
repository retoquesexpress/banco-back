package com.fpmislata.banco_back.persistence;

import com.fpmislata.banco_back.persistence.dao.jpa.AccountMovementJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.ClientJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.CreditCardJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.impl.AccountMovementJpaDaoImpl;
import com.fpmislata.banco_back.persistence.dao.jpa.impl.ClientJpaDaoImpl;
import com.fpmislata.banco_back.persistence.dao.jpa.impl.CreditCardJpaDaoImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.fpmislata.banco_back.persistence.dao.jpa")
@EntityScan(basePackages = "com.fpmislata.banco_back.persistence.dao.jpa.entity")
public class PersistenceConfig {
    @Bean
    public ClientJpaDao clientJpaDao() {
        return new ClientJpaDaoImpl();
    }

    @Bean
    public CreditCardJpaDao creditCardJpaDao() {return new CreditCardJpaDaoImpl();}

    @Bean
    public AccountMovementJpaDao accountMovementJpaDao() {
        return new AccountMovementJpaDaoImpl();
    }

}
