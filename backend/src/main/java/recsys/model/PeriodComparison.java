package recsys.model;

import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Represents the period of reconciliation.
 */


public class PeriodComparison {
    private int year;

    private int month;

    private ComparisonState status;

    private BankTransaction BankSum;

    private AccTransaction AccSum;

    private List<AccTransaction> accTransactionList;
    private List<BankTransaction> bankTransactionList;
}
