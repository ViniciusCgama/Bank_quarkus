package io.quarkus.bank.rest.service;

import io.quarkus.bank.domain.model.Account;
import io.quarkus.bank.domain.model.User;
import io.quarkus.bank.domain.repository.AccountRepository;
import io.quarkus.bank.rest.dto.AccountType;
import jakarta.inject.Inject;

import java.math.BigDecimal;

public class UserService {

    @Inject
    AccountRepository accountRepository;

    public void createUser(String name, Long userId) {
        User user = new User();
        user.setId(userId);
        user.setName(name);

        // Criar uma instância da entidade `Account`
        Account account = new Account();
        account.setId(userId);
        account.setUser(user);
        account.setType(AccountType.CURRENT);
        account.setBalance(BigDecimal.valueOf(0.0));

        user.setAccount(account);

        accountRepository.persist(user.getAccount());
    }
}