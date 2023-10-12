package recsys.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Represents a transaction.
 * TODO make it correspond to a table.
 */
@Entity
public class TransactionEntity {
    @Id
    private int id;
    private Date date;
    private double amount;
    private String account;


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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
