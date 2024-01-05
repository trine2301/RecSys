
package recsys.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;


/**
 * An entity that represents an accounting transaction.
 *
 * @author Trine Staverløkk
 * @version 0.1
 */
@Entity
@AllArgsConstructor
@Table(name = "accounting_transactions")
public class AccountingTransactionEntity {

    /**
     * The ID of the transaction.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The date of the transaction.
     */
    @NotNull
    private LocalDate date; //LocalDate?

    /**
     * the amount of the transaction.
     */
    @NotNull
    private double amount;

    /**
     * The description in the transaction.
     */
    private String description;

    /**
     * No-Arg constructor for objects of BankTransactionEntity type.
     */
    public AccountingTransactionEntity() {
        // Entities must have an empty constructor to allow dynamic
        // class inspection and instantiation at runtime.
        // Without it, the compiler will stop with and exception.
    }

    /**
     * Gets ID.
     *
     * @return Returns the ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID.
     *
     * @param id The ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets date.
     *
     * @return Returns the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date.
     *
     * @param date The date to set.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets amount.
     *
     * @return Returns the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount.
     *
     * @param amount The amount to set.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets description.
     *
     * @return Returns the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
