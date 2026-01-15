package com.fpmislata.banco_back.persistence.repository;

import com.fpmislata.banco_back.domain.model.enums.MovementType;
import com.fpmislata.banco_back.domain.model.enums.OriginMovement;
import com.fpmislata.banco_back.domain.repository.AccountRepository;
import com.fpmislata.banco_back.domain.repository.entity.AccountEntity;
import com.fpmislata.banco_back.domain.repository.entity.AccountMovementEntity;
import com.fpmislata.banco_back.domain.repository.entity.ClientEntity;
import com.fpmislata.banco_back.domain.repository.entity.CreditCardEntity;
import com.fpmislata.banco_back.mapper.AccountMapper;
import com.fpmislata.banco_back.mapper.AccountMovementMapper;
import com.fpmislata.banco_back.mapper.ClientMapper;
import com.fpmislata.banco_back.mapper.CreditCardMapper;
import com.fpmislata.banco_back.persistence.dao.jpa.AccountJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.AccountMovementJpaDao;
import com.fpmislata.banco_back.persistence.dao.jpa.entity.AccountMovementJpaEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class AccountRepositoryImpl implements AccountRepository {
        private final AccountJpaDao accountJpaDao;

        private final AccountMovementJpaDao accountMovementJpaDao;

        public AccountRepositoryImpl(AccountJpaDao accountJpaDao, AccountMovementJpaDao accountMovementJpaDao) {
                this.accountJpaDao = accountJpaDao;
                this.accountMovementJpaDao = accountMovementJpaDao;
        }

        @Override
        public List<AccountEntity> findAll() {
                return accountJpaDao.findAll()
                                .stream()

                                .map(accountJpaEntity -> {
                                        AccountEntity account = AccountMapper.getInstance()
                                                        .fromAccountJpaEntityToAccountEntity(accountJpaEntity);
                                        List<AccountMovementEntity> movements = accountJpaEntity.getCreditCards()
                                                        .stream()
                                                        .flatMap(creditCard -> accountMovementJpaDao
                                                                        .findByCreditCardOrigin(
                                                                                        creditCard.getCardNumber())
                                                                        .stream())
                                                        .map(AccountMovementMapper
                                                                        .getInstance()::fromAccountMovementJpaEntityToAccountMovementEntity)
                                                        .toList();
                                        return new AccountEntity(account.iban(), account.balance(), account.client(),
                                                        movements,
                                                        account.creditCards());
                                })
                                .toList();
        }

        @Override
        public List<AccountEntity> findByClient(ClientEntity client) {
                return accountJpaDao
                                .findByClient(ClientMapper.getInstance().fromClientEntityToClientJpaEntity(client))
                                .stream()

                                .map(accountJpaEntity -> {
                                        AccountEntity account = AccountMapper.getInstance()
                                                        .fromAccountJpaEntityToAccountEntity(accountJpaEntity);
                                        List<AccountMovementEntity> movements = accountJpaEntity.getCreditCards()
                                                        .stream()
                                                        .flatMap(creditCard -> accountMovementJpaDao
                                                                        .findByCreditCardOrigin(
                                                                                        creditCard.getCardNumber())
                                                                        .stream())
                                                        .map(AccountMovementMapper
                                                                        .getInstance()::fromAccountMovementJpaEntityToAccountMovementEntity)
                                                        .toList();
                                        return new AccountEntity(account.iban(), account.balance(), account.client(),
                                                        movements,
                                                        account.creditCards());
                                })
                                .toList();
        }

        @Override
        public AccountEntity getByIban(String iban) {
                return accountJpaDao.findByIban(iban)

                                .map(accountJpaEntity -> {
                                        AccountEntity account = AccountMapper.getInstance()
                                                        .fromAccountJpaEntityToAccountEntity(accountJpaEntity);
                                        List<AccountMovementEntity> movements = accountJpaEntity.getCreditCards()
                                                        .stream()
                                                        .flatMap(creditCard -> accountMovementJpaDao
                                                                        .findByCreditCardOrigin(
                                                                                        creditCard.getCardNumber())
                                                                        .stream())
                                                        .map(AccountMovementMapper
                                                                        .getInstance()::fromAccountMovementJpaEntityToAccountMovementEntity)
                                                        .toList();
                                        return new AccountEntity(account.iban(), account.balance(), account.client(),
                                                        movements,
                                                        account.creditCards());
                                })
                                .orElse(null);
        }

        @Override
        public Optional<AccountEntity> findByIban(String iban) {
                return accountJpaDao.findByIban(iban)
                                .map(accountJpaEntity -> {
                                        AccountEntity account = AccountMapper.getInstance()
                                                        .fromAccountJpaEntityToAccountEntity(accountJpaEntity);
                                        List<AccountMovementEntity> movements = accountJpaEntity.getCreditCards()
                                                        .stream()
                                                        .flatMap(creditCard -> accountMovementJpaDao
                                                                        .findByCreditCardOrigin(
                                                                                        creditCard.getCardNumber())
                                                                        .stream())
                                                        .map(AccountMovementMapper
                                                                        .getInstance()::fromAccountMovementJpaEntityToAccountMovementEntity)
                                                        .toList();
                                        return new AccountEntity(account.iban(), account.balance(), account.client(),
                                                        movements,
                                                        account.creditCards());
                                });
        }

        @Override
        public Optional<AccountEntity> findAccountByCreditCard(CreditCardEntity creditCardEntity) {
                return accountJpaDao
                                .findAccountByCreditCard(CreditCardMapper.getInstance()
                                                .fromCreditCardEntityToCreditCardJpaEntity(creditCardEntity))
                                .map(AccountMapper.getInstance()::fromAccountJpaEntityToAccountEntity);
        }

        @Override
        public AccountEntity depositMoney(AccountEntity accountEntity, Double amount, String concept,
                        String cardNumber) {
                accountEntity.depositMoney(amount);

                AccountMovementJpaEntity accountMovementJpaEntity = new AccountMovementJpaEntity();
                accountMovementJpaEntity.setAmount(amount);
                accountMovementJpaEntity.setConcept(concept);
                accountMovementJpaEntity.setOriginMovement(OriginMovement.TARJETA_BANCARIA);
                accountMovementJpaEntity.setMovementType(MovementType.DEPOSITAR);
                accountMovementJpaEntity.setDate(new Date());

                if (cardNumber != null) {
                        accountMovementJpaEntity.setCreditCardOrigin(cardNumber);
                } else if (accountEntity.creditCards() != null && !accountEntity.creditCards().isEmpty()) {
                        accountMovementJpaEntity.setCreditCardOrigin(accountEntity.creditCards().get(0).cardNumber());
                }

                var accountJpa = AccountMapper.getInstance().fromAccountEntityToAccountJpaEntity(accountEntity);
                var updatedAccountJpa = accountJpaDao.save(accountJpa);
                accountMovementJpaDao.insert(accountMovementJpaEntity);

                return AccountMapper.getInstance().fromAccountJpaEntityToAccountEntity(updatedAccountJpa);
        }

        @Override
        public AccountEntity withdrawMoney(AccountEntity accountEntity, Double amount, String concept,
                        String cardNumber) {
                accountEntity.withdrawMoney(amount);

                AccountMovementJpaEntity accountMovementJpaEntity = new AccountMovementJpaEntity();
                accountMovementJpaEntity.setAmount(amount);
                accountMovementJpaEntity.setConcept(concept);
                accountMovementJpaEntity.setOriginMovement(OriginMovement.TARJETA_BANCARIA);
                accountMovementJpaEntity.setMovementType(MovementType.RETIRAR);
                accountMovementJpaEntity.setDate(new Date());

                if (cardNumber != null) {
                        accountMovementJpaEntity.setCreditCardOrigin(cardNumber);
                } else if (accountEntity.creditCards() != null && !accountEntity.creditCards().isEmpty()) {
                        accountMovementJpaEntity.setCreditCardOrigin(accountEntity.creditCards().get(0).cardNumber());
                }

                var accountJpa = AccountMapper.getInstance().fromAccountEntityToAccountJpaEntity(accountEntity);
                var updatedAccountJpa = accountJpaDao.save(accountJpa);
                accountMovementJpaDao.insert(accountMovementJpaEntity);

                return AccountMapper.getInstance().fromAccountJpaEntityToAccountEntity(updatedAccountJpa);
        }
}
