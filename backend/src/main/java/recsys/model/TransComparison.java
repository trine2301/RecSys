package recsys.model;

import javax.validation.constraints.NotNull;

/**
 * Entity of the results from transactionService.
 */


public class TransComparison {

    @NotNull
    private BankTransactionEntity bankTransactionEntity;

    @NotNull
    private AccTransaction accTransaction;


    private Double discrepancyAmount;




}
