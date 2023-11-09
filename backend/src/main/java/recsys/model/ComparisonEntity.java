package recsys.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Entity of the results from transactionService. Runtime entity.
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ComparisonEntity {


    // Composite primary key of all three fields.
    @Id
    @NonNull
    private ComparisonId id;

    //One comparison could exist in multiple periods.
    @ManyToOne
    @NonNull
    private PeriodEntity periodComparison;

    @OneToOne(optional = true)
    @NonNull
    private BankTransactionEntity bankTransactionEntity;

    @OneToOne(optional = true)
    @NonNull
    private AccountingTransactionEntity accountingTransactionEntity;



}
