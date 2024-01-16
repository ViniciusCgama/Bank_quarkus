package io.quarkus.bank.domain.repository;

import io.quarkus.bank.domain.model.Account;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.math.BigDecimal;

@ApplicationScoped
public class AccountRepository implements PanacheRepository<Account> {

    @PersistenceContext
    private EntityManager entityManager;

    public Account save(Account account) {
        entityManager.persist(account);
        return account;
    }

    public Account findById(Long accountId) {
        return findByIdOptional(accountId).orElse(null); // Use findByIdOptional() for clarity
    }

}