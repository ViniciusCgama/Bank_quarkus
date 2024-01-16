package io.quarkus.bank.rest.controller;

import io.quarkus.bank.domain.model.Account;
import io.quarkus.bank.domain.model.User;
import io.quarkus.bank.domain.repository.AccountRepository;
import io.quarkus.bank.domain.repository.UserRepository;
import io.quarkus.bank.rest.dto.AccountType;
import io.quarkus.bank.rest.dto.CreateUserAccountRequest;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.bank.rest.dto.CreateUserRequest;
import io.quarkus.bank.rest.dto.ResponseError;
import io.vertx.core.DeploymentOptions;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.math.BigDecimal;
import java.util.Set;



@Path("api/v1/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final AccountRepository repositoryA;
    private UserRepository repository;
    private Validator validator;
    private DeploymentOptions mapper;

    @Inject
    public UserResource(UserRepository repository, AccountRepository accountRepository, Validator validator, AccountRepository repositoryA) {
        this.repository = repository;
        this.repositoryA = repositoryA;
        this.validator = validator;
    }

    @POST
    @Transactional
    public Response createUser(CreateUserAccountRequest request) {

        Set<ConstraintViolation<CreateUserAccountRequest>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            return ResponseError
                    .createFromValidation(violations)
                    .withStatusCode(ResponseError.UNPROCESSABLE_ENTITY_STATUS);
        }

        User user = new User();
        user.setName(request.getName());
        user.setAge(request.getAge());
        user.setTell(request.getTell());
        user.setAdress(request.getAdress());
        user.setType(request.getType());

        Account account = new Account();
        account.setId(user.getId());
        account.setName(user.getName());
        account.setType(request.getType());
        account.setBalance(BigDecimal.valueOf(0.0));
        if (account.getType() == null) {
            throw new BadRequestException("type must not be null");
        }

        repository.persist(user);
        repositoryA.persist(account);

        return Response
                .status(Response.Status.CREATED.getStatusCode())
                .entity(user).entity(account)
                .build();
    }

    @GET
    public Response listAllUsers() {
        PanacheQuery<User> query = repository.findAll();
        return Response.ok(query.list()).build();
    }
    }
