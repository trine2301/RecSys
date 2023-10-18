package recsys.service;

import groovy.lang.Singleton;
import recsys.model.TransactionEntity;
import recsys.model.ComparisonEntity;


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
    public ComparisonEntity compare(TransactionEntity transaction1, TransactionEntity transaction2) {
        // Compare transaction1 and transaction2 and return the differences
        return null;
    }


/*    public TransactionDifference compare(Transaction transaction1, Transaction transaction2) {
        TransactionDifference difference = new TransactionDifference();
        difference.setAmountDifference(transaction1.getAmount() - transaction2.getAmount());
        difference.setDateDifference(transaction1.getDate().compareTo(transaction2.getDate()));
        difference.setAccountDifference(transaction1.getAccount().equals(transaction2.getAccount()) ? "Same" : "Different");
        return difference;
    }*/
}


