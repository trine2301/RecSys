package recsys.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity to store the differences (results) from transactionService.
 * Can be expanded to find where the difference lies, and not only the
 */

@Entity
@Table(name = "comparison")
public class ComparisonEntity {
    @EmbeddedId //composite primary key
    private ComparisonID id;

    @NotNull
    private Double amount;

    private Double discrepancyPosition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private TransactionEntity transaction;
}
