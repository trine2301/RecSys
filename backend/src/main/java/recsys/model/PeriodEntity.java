package recsys.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


/**
 * Represents the period of reconciliation.
 */

@Entity
@Table(name = "period_comparison")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PeriodEntity {

    @EmbeddedId
    private PeriodId periodId;
    private int year;

    private int month;

    @OneToMany
    private List<AccountingTransactionEntity> accountingTransactions;

    @OneToMany
    private List<BankTransactionEntity> bankTransactions;
    @OneToMany
    private List<ComparisonEntity> comparisons;

    private Result status;


    private int BankSum;


    private int AccSum;


}
