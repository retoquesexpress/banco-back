package com.fpmislata.banco_back.config;

import com.fpmislata.banco_back.domain.repository.AccountMovementRepository;
import com.fpmislata.banco_back.domain.repository.AccountRepository;
import com.fpmislata.banco_back.domain.repository.ClientRepository;
import com.fpmislata.banco_back.domain.repository.CreditCardRepository;
import com.fpmislata.banco_back.domain.service.AccountMovementService;
import com.fpmislata.banco_back.domain.service.AccountService;
import com.fpmislata.banco_back.domain.service.ClientService;
import com.fpmislata.banco_back.domain.service.CreditCardService;
import com.fpmislata.banco_back.domain.service.PagoTarjetaService;
import com.fpmislata.banco_back.domain.service.impl.AccountMovementServiceImpl;
import com.fpmislata.banco_back.domain.service.impl.AccountServiceImpl;
import com.fpmislata.banco_back.domain.repository.AuthRepository;
import com.fpmislata.banco_back.domain.service.AuthService;
import com.fpmislata.banco_back.domain.service.impl.AuthServiceImpl;
import com.fpmislata.banco_back.domain.service.impl.ClientServiceImpl;
import com.fpmislata.banco_back.domain.service.impl.CreditCardServiceImpl;
import com.fpmislata.banco_back.domain.service.impl.PagoTarjetaServiceImpl;
import com.fpmislata.banco_back.persistence.PersistenceConfig;
import com.fpmislata.banco_back.persistence.dao.jpa.AccountJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.AccountMovementJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.ClientJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.CreditCardJpaDao;
import com.fpmislata.banco_back.persistence.repository.AccountMovementRepositoryImpl;
import com.fpmislata.banco_back.persistence.repository.AccountRepositoryImpl;
import com.fpmislata.banco_back.persistence.repository.AuthRepositoryImpl;
import com.fpmislata.banco_back.persistence.repository.ClientRepositoryImpl;
import com.fpmislata.banco_back.persistence.repository.CreditCardRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Import(PersistenceConfig.class)
public class SpringConfig {

    @Bean
    public AccountRepository accountRepository(AccountJpaDao accountJpaDao,
            AccountMovementJpaDao accountMovementJpaDao) {
        return new AccountRepositoryImpl(accountJpaDao, accountMovementJpaDao);
    }

    @Bean
    public AccountService accountService(AccountRepository accountRepository) {
        return new AccountServiceImpl(accountRepository);
    }

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

    @Bean
    public CreditCardRepository creditCardRepository(CreditCardJpaDao creditCardRepositoryJpaDao) {
        return new CreditCardRepositoryImpl(creditCardRepositoryJpaDao);
    }

    @Bean
    public CreditCardService creditCardService(CreditCardRepository creditCardRepository) {
        return new CreditCardServiceImpl(creditCardRepository);
    }

    @Bean
    public AuthService authService(AuthRepository authRepository) {
        return new AuthServiceImpl(authRepository);
    }

    @Bean
    public AuthRepository authRepository(ClientJpaDao clientJpaDao) {
        return new AuthRepositoryImpl(clientJpaDao);
    }

    @Bean
    public PagoTarjetaService pagoTarjetaService(ClientService clientService, AccountService accountService,
            CreditCardService creditCardService) {
        return new PagoTarjetaServiceImpl(clientService, accountService, creditCardService);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);

            }
        };
    }
}
