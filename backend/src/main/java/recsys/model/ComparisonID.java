package recsys.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Represents the composite primary key of ComparisonEntity.
 */
@Embeddable
public class ComparisonID implements Serializable {
    private Long transactionID;
    private Long comparisonID;

    public Long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public Long getComparisonID() {
        return comparisonID;
    }

    public void setComparisonID(Long comparisonID) {
        this.comparisonID = comparisonID;
    }

}
