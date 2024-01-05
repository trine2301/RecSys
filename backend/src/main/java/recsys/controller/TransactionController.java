package recsys.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import recsys.model.AccountingTransactionEntity;
import recsys.model.BankTransactionEntity;
import recsys.model.PeriodEntity;
import recsys.repository.AccountingTransactionRepository;
import recsys.repository.BankTransactionRepository;
import recsys.repository.PeriodRepository;

import java.util.List;


/**
 * Controller to handle the HTTP requests regarding transactions.
 *
 * @author Trine Staverløkk
 * @version 0.1
 */
@Controller("/transactions")
public class TransactionController {

    private final AccountingTransactionRepository accountingTransactionRepository;
    private final BankTransactionRepository bankTransactionRepository;
    private final PeriodRepository periodRepository;

    /**
     * Constructor
     *
     * @param accountingRepository
     * @param bankRepository
     * @param repository
     */
    public TransactionController(AccountingTransactionRepository accountingRepository, BankTransactionRepository bankRepository, PeriodRepository repository) {
        this.accountingTransactionRepository = accountingRepository;
        this.bankTransactionRepository = bankRepository;
        periodRepository = repository;
    }


    @Get("/accounting")
    public List<AccountingTransactionEntity> getAccountingTransactions() {
        return accountingTransactionRepository.findAll();
    }

    @Get("/bank")
    public List<BankTransactionEntity> getBankTransactions() {
        return bankTransactionRepository.findAll();
    }

    @Get("/period_comparison")
    public List<PeriodEntity> getPeriodComparisons() {
        return periodRepository.findAll();
    }
}



