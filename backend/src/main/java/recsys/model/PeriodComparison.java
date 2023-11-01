package recsys.model;

import java.util.List;

/**
 * Represents the period of reconciliation.
 */


public class PeriodComparison {
    private int year;

    private int month;

    private ComparisonState status;

    private BankTransactionEntity BankSum;

    private AccTransaction AccSum;

    private List<AccTransaction> accTransactionList;
    private List<BankTransactionEntity> bankTransactionEntityList;
}
