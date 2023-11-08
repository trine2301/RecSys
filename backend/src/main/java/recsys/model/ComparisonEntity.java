package recsys.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Entity of the results from transactionService. Runtime entity.
 */

//@Entity what should ID be? Composite key of
public class ComparisonEntity {

    @NotNull
    private BankTransactionEntity bankTransactionEntity;

    @NotNull
    private AccountingTransactionEntity accountingTransactionEntity;


    private ComparisonState comparisonState;

    // Results?

}
