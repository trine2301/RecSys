package recsys.model;


import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "account")
public class AccountEntity {
    @Id
    @NotNull
    private String accountNumber;

    @NotNull
    private String name;

    @NotNull
    private AccountType accountType;

    @NotNull
    private double balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions;



    public String getAccountNumber() {
        return this.accountNumber;
    }


    public String getName() {
        return this.name;
    }

    public AccountType getAccountType() {
        return this.accountType;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "AccountEntity{" +
            "accountNumber='" + accountNumber + '\'' +
            ", name='" + name + '\'' +
            ", accountType=" + accountType +
            ", balance=" + balance +
            '}';
    }
}
