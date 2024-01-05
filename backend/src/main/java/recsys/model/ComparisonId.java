package recsys.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The ID of a comparison.
 *
 * @author Trine Staverløkk
 * @version 0.1
 */
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ComparisonId implements Serializable {
    private Result result;
    private BankTransactionEntity bankTransactionEntity;
    private AccountingTransactionEntity accountingTransactionEntity;

    //No need for EqualsAndHashCode, since I use @Data. Would otherwise need if composite key would work.

}
