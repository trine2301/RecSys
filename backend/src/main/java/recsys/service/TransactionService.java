package recsys.service;

import groovy.lang.Singleton;
import recsys.model.BankTransactionEntity;
import recsys.model.TransComparison;


/**
 * Represents a comparison model. Contains logic to compare two files etc.
 *
 */
@Singleton
public class TransactionService {

    /**
     * Constructor
     */
    public TransactionService() {
    }

    /**
     * Compares two transactions.
     * @param transaction1 Accounting transaction to be compared
     * @param transaction2 Bank transaction to be compared
     * @return The differences
     */
    public TransComparison compare(BankTransactionEntity transaction1, BankTransactionEntity transaction2) {
        // Compare transaction1 and transaction2 and return the differences
        return null;
    }

    public void testing() {
        System.out.println("hello");
    }

    public static void main(String[] args) {
        System.out.println("Hello");
    }
}


