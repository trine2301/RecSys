package recsys.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.util.Objects;

/**
 * Represents the primary key of PeriodEntity.
 */

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeriodId implements Serializable {

    //final?
    private long id;

    private ComparisonEntity periodComparison;
    private int bankSum;
    private int accSum;


    //dont need, siden @Data, men yolo
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PeriodId id = (PeriodId) o;
        return this.id == id.id && bankSum == id.bankSum && accSum == id.accSum && Objects.equals(periodComparison, id.periodComparison);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, periodComparison, bankSum, accSum);
    }
}
