package recsys.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Entity of the results from transactionService. Runtime entity.
 */

@Entity
public class ComparisonEntity {


    @NotNull
    private BankTransactionEntity bankTransactionEntity;

    @NotNull
    private AccountingTransactionEntity accountingTransactionEntity;


    private ComparisonState comparisonState;



}
