package recsys.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Represents a bank transaction.
 *
 */
@Entity
@Table(name = "BankTransaction")
public class BankTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionID;

    @NotNull
    private Date date;

    @NotNull
    private double amount;


    private String description;

}
