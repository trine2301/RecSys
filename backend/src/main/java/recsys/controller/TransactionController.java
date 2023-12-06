package recsys.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import recsys.model.AccountingTransactionEntity;
import recsys.model.BankTransactionEntity;
import recsys.repository.AccountingTransactionRepository;
import recsys.repository.BankTransactionRepository;

import java.util.List;

@Controller("/transactions")
public class TransactionController {

    private final AccountingTransactionRepository accountingTransactionRepository;
    private final BankTransactionRepository bankTransactionRepository;

    public TransactionController(AccountingTransactionRepository accountingRepository, BankTransactionRepository bankRepository) {
        this.accountingTransactionRepository = accountingRepository;
        this.bankTransactionRepository = bankRepository;
    }

    @Get("/accounting")
    public List<AccountingTransactionEntity> getAccountingTransactions() {
        return accountingTransactionRepository.findAll();
    }

    @Get("/bank")
    public List<BankTransactionEntity> getBankTransactions() {
        return bankTransactionRepository.findAll();
    }
}



