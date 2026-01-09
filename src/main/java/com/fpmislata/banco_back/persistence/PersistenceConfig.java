package com.fpmislata.banco_back.persistence;

import com.fpmislata.banco_back.persistence.dao.jpa.ClientJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.impl.ClientJpaDaoImpl;
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

}
