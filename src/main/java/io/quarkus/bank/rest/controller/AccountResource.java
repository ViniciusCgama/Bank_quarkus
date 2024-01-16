package io.quarkus.bank.rest.controller;

import io.quarkus.bank.domain.model.Account;
import io.quarkus.bank.domain.repository.AccountRepository;
import io.quarkus.bank.rest.dto.AccountType;
import io.quarkus.bank.rest.dto.DepositRequest;
import io.quarkus.bank.rest.dto.WithdrawRequest;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static io.quarkus.bank.rest.dto.AccountType.SAVINGS;
import static io.quarkus.bank.rest.dto.ResponseError.INSUFFICIENT_FUNDS;
import static jakarta.ws.rs.core.Response.Status.*;

@ApplicationScoped
@Path("/api/v1/accounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountResource {

    @Inject
    AccountRepository accountRepository;

    @GET
    @Path("{id}/balance")
    public Response getBalance(@PathParam("id") Long accountId) {
        Account account = accountRepository.findById(accountId);
        if (account == null) {
            return Response.status(NOT_FOUND).build();
        }

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("name", account.getName());
        responseData.put("type", account.getType());
        responseData.put("balance", account.getBalance());

        return Response.ok(responseData).build();
    }

    @POST
    @Transactional
    @Path("/{id}/deposit")
    public Response deposit(@PathParam("id") Long accountId, DepositRequest depositRequest) {

        Account account = accountRepository.findById(accountId);

        if (account == null) {
            return Response.status(NOT_FOUND).build();
        }

        BigDecimal amount = depositRequest.getAmount();

        if (account.getType() == AccountType.SAVINGS) {
            amount = amount.add(new BigDecimal("0.50")); // Adicione 50 centavos
        }

        account.setBalance(account.getBalance().add(amount));

        accountRepository.save(account);

        return Response.ok().build();
    }


    @POST
    @Transactional
    @Path("/{id}/withdraw")
    public Response withdraw(@PathParam("id") Long accountId, WithdrawRequest withdrawRequest) {

        Account account = accountRepository.findById(accountId);

        if (account == null) {
            return Response.status(NOT_FOUND).build();
        }

        BigDecimal amount = withdrawRequest.getAmount();

        if (account.getBalance().compareTo(amount) < 0) {
            return Response.status(INSUFFICIENT_FUNDS).build();
        }

        account.setBalance(account.getBalance().subtract(amount));

        accountRepository.save(account);

        return Response.ok().build();
    }

}


