package recsys.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Entity of the results from transactionService. Runtime entity.
 */

//@Entity
//@Table(name = "comparisons")
public class ComparisonEntity {


    // Composite primary key of bankEnt, AccEnt and result.
//    @EmbeddedId
//    @NonNull
//    private ComparisonId id;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;


    @OneToOne
    private BankTransactionEntity bankTransactionEntity;

    @OneToOne
    private AccountingTransactionEntity accountingTransactionEntity;

    private Result result;

    private Status status;


    public ComparisonEntity() {

    }





    public void setBankTransactionEntity(BankTransactionEntity bankTransactionEntity) {
        this.bankTransactionEntity = bankTransactionEntity;
    }

    public void setAccountingTransactionEntity(AccountingTransactionEntity accountingTransactionEntity) {
        this.accountingTransactionEntity = accountingTransactionEntity;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void setStatus(Status status) {
        this.status = status;
    }



    public BankTransactionEntity getBankTransactionEntity() {
        return bankTransactionEntity;
    }

    public AccountingTransactionEntity getAccountingTransactionEntity() {
        return accountingTransactionEntity;
    }

    public Result getResult() {
        return result;
    }

    public Status getStatus() {
        return status;
    }
}
