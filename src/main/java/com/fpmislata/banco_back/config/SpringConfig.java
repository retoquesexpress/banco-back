package com.fpmislata.banco_back.config;

import com.fpmislata.banco_back.domain.repository.AccountRepository;
import com.fpmislata.banco_back.domain.service.AccountService;
import com.fpmislata.banco_back.domain.service.impl.AccountServiceImpl;
import com.fpmislata.banco_back.persistence.PersistenceConfig;
import com.fpmislata.banco_back.persistence.dao.jpa.AccountJpaDao;
import com.fpmislata.banco_back.persistence.repository.AccountRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PersistenceConfig.class)
public class SpringConfig {

    @Bean
    public AccountRepository accountRepository(AccountJpaDao accountJpaDao) {
        return new AccountRepositoryImpl(accountJpaDao);
    }

    @Bean
    public AccountService accountService(AccountRepository accountRepository) {
        return new AccountServiceImpl(accountRepository);
    }
}
