package recsys.service;

import groovy.lang.Singleton;
import recsys.model.Transaction;
import recsys.model.database.TransactionDifference;

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
    public TransactionDifference compare(Transaction transaction1, Transaction transaction2) {
        // Compare transaction1 and transaction2 and return the differences
        return null;
    }

}


