package recsys.service;

import groovy.lang.Singleton;
import lombok.RequiredArgsConstructor;
import recsys.model.AccountingTransactionEntity;
import recsys.model.BankTransactionEntity;
import recsys.repository.AccountingTransactionRepository;
import recsys.repository.BankTransactionRepository;

import jakarta.inject.Inject;
import java.util.List;


/**
 * Represents a comparison model. Contains logic to compare two files etc.
 *
 */
@Singleton
public class TransactionService {


    private AccountingTransactionRepository accountingTransactionRepository;

    private BankTransactionRepository bankTransactionRepository;

    private double bankTotal;
    private double accountingTotal;

    @Inject
    public TransactionService(AccountingTransactionRepository accountingTransactionRepository, BankTransactionRepository bankTransactionRepository) {
        this.accountingTransactionRepository = accountingTransactionRepository;
        this.bankTransactionRepository = bankTransactionRepository;
    }

    public TransactionService() {

    }


    /**
     * Sets the total amount for bank and for accounting transactions, based on the amounts in each transaction in BankTransactionEntity and AccountingTransactionEntity.
     */
    public void setBankAndAccountingTotalAmount() {
        /*List<BankTransactionEntity> bankTransactions = bankTransactionRepository.findAll();
        List<AccountingTransactionEntity> accountingTransactions = accountingTransactionRepository.findAll();

        //implement logic here:
        bankTotal = 0;
        accountingTotal = 0;

        for (BankTransactionEntity bankTransaction : bankTransactions) {
            bankTotal += bankTransaction.getAmount();
        }*/

        bankTotal = 2;
        accountingTotal = 0;

    }



    /**
     * Compares total amount
     * @return returns total discrepancy.
     */
    public double compareTotalAmount() {

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
     *
     * @return Returns True if there is a discrepancy between total amount on the transactions.
     */
    public boolean checkIfDiscrepancyExists() {

        boolean isDiscrepancy = false;

        setBankAndAccountingTotalAmount();

        if(bankTotal - accountingTotal != 0) {
            isDiscrepancy = true;
        }
        return isDiscrepancy;
    }


    public static void main(String[] args) {
        TransactionService transactionService = new TransactionService();
        transactionService.checkIfDiscrepancyExists();

        boolean checking = transactionService.checkIfDiscrepancyExists();
        double compareTotal = transactionService.compareTotalAmount();
        transactionService.setBankAndAccountingTotalAmount();


        System.out.println("Checking if there is dicrepancies, and it is: " + checking);
        System.out.println("The difference is: " + compareTotal);
    }
}




