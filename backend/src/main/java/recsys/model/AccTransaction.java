package recsys.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Represents an accounting transaction.
 *
 */
@Entity
@Table(name = "accTransaction")
public class AccTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionID;

    @NotNull
    private Date date;

    @NotNull
    private double accSum;


    private String description;

}
