package recsys.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * Represents the period of reconciliation.
 */

@Entity
@Table(name = "period_comparison")
@Data
public class PeriodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private double totalDiscrepancyAmount;

    private double bankTotal;

    private double accTotal;

    public PeriodEntity() {
    }

    public PeriodEntity(Long id, LocalDate startDate, LocalDate endDate, double totalDiscrepancyAmount, double bankTotal, double accTotal) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalDiscrepancyAmount = totalDiscrepancyAmount;
        this.bankTotal = bankTotal;
        this.accTotal = accTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getTotalDiscrepancyAmount() {
        return totalDiscrepancyAmount;
    }

    public void setTotalDiscrepancyAmount(double totalDiscrepancyAmount) {
        this.totalDiscrepancyAmount = totalDiscrepancyAmount;
    }

    public double getBankTotal() {
        return bankTotal;
    }

    public void setBankTotal(double bankTotal) {
        this.bankTotal = bankTotal;
    }

    public double getAccTotal() {
        return accTotal;
    }

    public void setAccTotal(double accTotal) {
        this.accTotal = accTotal;
    }
}
