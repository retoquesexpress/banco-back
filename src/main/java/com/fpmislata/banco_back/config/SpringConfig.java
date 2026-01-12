package com.fpmislata.banco_back.config;

import com.fpmislata.banco_back.domain.repository.AccountMovementRepository;
import com.fpmislata.banco_back.domain.repository.ClientRepository;
import com.fpmislata.banco_back.domain.service.AccountMovementService;
import com.fpmislata.banco_back.domain.service.ClientService;
import com.fpmislata.banco_back.domain.service.impl.AccountMovementServiceImpl;
import com.fpmislata.banco_back.domain.service.impl.ClientServiceImpl;
import com.fpmislata.banco_back.persistence.PersistenceConfig;
import com.fpmislata.banco_back.persistence.dao.jpa.AccountMovementJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.ClientJpaDao;
import com.fpmislata.banco_back.persistence.repository.AccountMovementRepositoryImpl;
import com.fpmislata.banco_back.persistence.repository.ClientRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceConfig.class)
public class SpringConfig {
    @Bean
    public ClientRepository clientRepository(ClientJpaDao clientJpaDao) {
        return new ClientRepositoryImpl(clientJpaDao);
    }

    @Bean
    public ClientService clientService(ClientRepository clientRepository) {
        return new ClientServiceImpl(clientRepository);
    }

    @Bean
    public AccountMovementRepository accountMovementRepository(AccountMovementJpaDao accountMovementJpaDao) {
        return new AccountMovementRepositoryImpl(accountMovementJpaDao);
    }

    @Bean
    public AccountMovementService accountMovementService(AccountMovementRepository accountMovementRepository) {
        return new AccountMovementServiceImpl(accountMovementRepository);
    }
}
