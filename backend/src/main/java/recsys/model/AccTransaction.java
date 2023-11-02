package recsys.model;

import javax.persistence.Column;
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
@Table(name = "AccTransaction")
public class AccTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accTransactionId;

    @NotNull
    private Date date;

    @NotNull
    private double amount;


    private String description;

}
