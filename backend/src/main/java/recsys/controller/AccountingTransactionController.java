package recsys.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import recsys.model.AccountingTransactionEntity;
import recsys.repository.AccountingTransactionRepository;

import java.util.List;

/**
 * Controller to handle the HTTP requests.
 */
@Controller("/accounting_transactions")
public class AccountingTransactionController {

    private final AccountingTransactionRepository accountingTransactionRepository;

    public AccountingTransactionController(AccountingTransactionRepository repository) {
        accountingTransactionRepository = repository;
    }


    /**
     * TODO write documentation
     * @return
     */
    @Get
    public List<AccountingTransactionEntity> getAccountingTransactions() {
        return accountingTransactionRepository.findAll();
    }

}

