package recsys.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Represents a transaction.
 *
 */
@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_number")
    private AccountEntity account;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionID;

    @NotNull
    private Date date;

    @NotNull
    private double amount;

    @NotNull
    private TransactionType transactionType;

    @NotNull
    private ComparisonState comparisonState;

    private String description;


    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public ComparisonState getComparisonState() {
        return comparisonState;
    }

    public void setComparisonState(ComparisonState comparisonState) {
        this.comparisonState = comparisonState;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TransactionEntity{" +
            "account=" + account +
            ", transactionID=" + transactionID +
            ", date=" + date +
            ", amount=" + amount +
            ", transactionType=" + transactionType +
            ", comparisonState=" + comparisonState +
            ", description='" + description + '\'' +
            '}';
    }
}
