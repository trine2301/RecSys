package recsys.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import recsys.model.AccountEntity;
import recsys.repository.AccountRepository;

import java.util.List;

/**
 * Controller responsible for handling requests related to accounts.
 *
 * Endpoint:
 * GET /accounts
 *
 * Response:
 * A list of AccountEntity objects is returned.
 *
 * Error Handling:
 * If an error occurs while processing the request, a 500 Internal Server Error is returned with a message describing the error.
 *
 */
@Controller("/account")
public class AccountController {

    private final AccountRepository accountRepository;

    public AccountController(AccountRepository repository) {
        accountRepository = repository;
    }


    @Get
    public List<AccountEntity> getAccounts() {
        return (List<AccountEntity>) accountRepository.findAll();
    }
}

