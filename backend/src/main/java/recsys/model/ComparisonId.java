package recsys.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ComparisonId implements Serializable {
    private PeriodEntity periodComparison;
    private BankTransactionEntity bankTransactionEntity;
    private AccountingTransactionEntity accountingTransactionEntity;

}
