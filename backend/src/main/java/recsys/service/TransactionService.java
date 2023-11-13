package recsys.service;

import recsys.model.AccountingTransactionEntity;
import recsys.model.BankTransactionEntity;
//import recsys.model.ComparisonEntity;
import recsys.repository.AccountingTransactionRepository;
import recsys.repository.BankTransactionRepository;

import jakarta.inject.Singleton;
import java.util.List;


/**
 * Represents a comparison model. Contains logic to compare two files etc.
 *
 */
@Singleton
public class TransactionService {

    private final AccountingTransactionRepository accountingTransactionRepository;
    private final BankTransactionRepository bankTransactionRepository;
    private double bankTotal;
    private double accountingTotal;

    public TransactionService(AccountingTransactionRepository accountingTransactionRepository, BankTransactionRepository bankTransactionRepository) {
        this.accountingTransactionRepository = accountingTransactionRepository;
        this.bankTransactionRepository = bankTransactionRepository;
    }


    /**
     * Sets the total amount for bank and for accounting transactions, based on the amounts in each transaction in BankTransactionEntity and AccountingTransactionEntity.
     */

    public void setBankAndAccountingTotalAmount() {
        List<BankTransactionEntity> bankTransactions = bankTransactionRepository.findAll();
        List<AccountingTransactionEntity> accountingTransactions = accountingTransactionRepository.findAll();

        bankTotal = 0;
        accountingTotal = 0;

        for (BankTransactionEntity bankTransaction : bankTransactions) {
            bankTotal += bankTransaction.getAmount();
        }
        for (AccountingTransactionEntity accountingTransaction : accountingTransactions) {
            bankTotal += accountingTransaction.getAmount();
        }
    }


    /**
     * Compares total amount
     * @return returns total discrepancy.
     */
    public double getDiscrepancyAmount() {

        double discrepancyAmount = 0;

        setBankAndAccountingTotalAmount();

        if(checkIfDiscrepancyExists() == true) {
            discrepancyAmount = bankTotal - accountingTotal;
        } if(discrepancyAmount < 0) {
            discrepancyAmount = accountingTotal - bankTotal;
        }
        return discrepancyAmount;
    }


    /**
     * Checks if there is any discrepancy on the total amount between bankTransactions and AccountingTransactions.
     * @return Returns True if there is a discrepancy.
     */
    public boolean checkIfDiscrepancyExists() {

        boolean isDiscrepancy = false;

        setBankAndAccountingTotalAmount();

        if(bankTotal - accountingTotal != 0) {
            isDiscrepancy = true;
        }
        return isDiscrepancy;
    }

}






