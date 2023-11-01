package recsys.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import recsys.model.BankTransactionEntity;
import recsys.repository.BankTransactionRepository;

import java.util.List;

/**
 * Controller to handle the HTTP requests.
 */
@Controller("/banktransactions")
public class BankTransactionController {

    private final BankTransactionRepository bankTransactionRepository;

    public BankTransactionController(BankTransactionRepository repository) {
        bankTransactionRepository = repository;
    }


    /**
     * TODO write documentation
     * @return
     */
    @Get
    public List<BankTransactionEntity> getBankTransactions() {
        return bankTransactionRepository.findAll();
    }

}

