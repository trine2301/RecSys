package recsys.model;

import javax.persistence.OneToOne;

/**
 * Entity of the results from transactionService. Runtime entity.
 */
public class ComparisonEntity {

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
