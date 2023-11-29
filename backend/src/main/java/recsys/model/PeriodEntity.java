package recsys.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
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

    @Id
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany
    private List<AccountingTransactionEntity> accountingTransactions;

    @OneToMany
    private List<BankTransactionEntity> bankTransactions;

    private Result status;



}
