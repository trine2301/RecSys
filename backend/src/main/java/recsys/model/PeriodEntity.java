package recsys.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * Represents the period of reconciliation.
 *
 * @author Trine Staverløkk
 * @version 0.1
 */

@Entity
@Table(name = "period_comparison")
public class PeriodEntity {

    /**
     * The id of the entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The start date of the compared period.
     */
    private LocalDate startDate;

    /**
     * The end date of the compared period.
     */
    private LocalDate endDate;

    /**
     * The total discrepancy amount between bank- and accounting transactions of the compared period.
     */
    private double totalDiscrepancyAmount;

    /**
     * The total amount of bank transactions of the compared period.
     */
    private double bankTotal;

    /**
     * The total amount of accounting transactions of the compared period.
     */
    private double accTotal;

    /**
     * No-Arg constructor for objects of BankTransactionEntity type.
     */
    public PeriodEntity() {
        // Entities must have an empty constructor to allow dynamic class inspection and instantiation at runtime.
        // Without it, the run will be stopped by an exception.
    }

    public PeriodEntity(Long id, LocalDate startDate, LocalDate endDate, double totalDiscrepancyAmount, double bankTotal, double accTotal) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalDiscrepancyAmount = totalDiscrepancyAmount;
        this.bankTotal = bankTotal;
        this.accTotal = accTotal;
    }

    /**
     * Gets ID.
     * @return Returns the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets ID.
     * @param id the ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets ID.
     * @return Returns the startDate.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date.
     * @param startDate the start date.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets ID.
     * @return Returns the id.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date.
     * @param endDate the end date.
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets totalDiscrepancyAmount.
     * @return Returns the totalDiscrepancyAmount.
     */
    public double getTotalDiscrepancyAmount() {
        return totalDiscrepancyAmount;
    }

    /**
     * Sets total discrepancyAmount.
     * @param totalDiscrepancyAmount the total discrepancyAmount.
     */
    public void setTotalDiscrepancyAmount(double totalDiscrepancyAmount) {
        this.totalDiscrepancyAmount = totalDiscrepancyAmount;
    }

    /**
     * Gets bankTotal.
     * @return Returns the bankTotal.
     */
    public double getBankTotal() {
        return bankTotal;
    }

    /**
     * Sets bankTotal.
     * @param bankTotal the bankTotal.
     */
    public void setBankTotal(double bankTotal) {
        this.bankTotal = bankTotal;
    }

    /**
     * Gets accTotal.
     * @return Returns the accTotal.
     */
    public double getAccTotal() {
        return accTotal;
    }

    /**
     * Sets account total.
     * @param accTotal the account total.
     */
    public void setAccTotal(double accTotal) {
        this.accTotal = accTotal;
    }
}
