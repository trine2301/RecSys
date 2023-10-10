package recsys.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import recsys.model.AccountEntity;
import java.util.List;
import recsys.repository.AccountRepository;


/**
 * Controller responsible for handling requests related to accounts.
 * <p>
 * Endpoint:
 * GET /accounts
 * <p>
 * Response:
 * A list of AccountEntity objects is returned.
 * <p>
 * Error Handling:
 * If an error occurs while processing the request,
 * a 500 Internal Server Error is returned with a message describing the error.
 *
 */
@Controller("/accounts")
public class AccountController {

    private final AccountRepository accountRepository;

    /**
     * Constructor
     * @param repository
     */
    public AccountController(AccountRepository repository) {
        accountRepository = repository;
    }


    @Get
    public List<AccountEntity> getAccounts() {
        return accountRepository.findAll();
    }
}

