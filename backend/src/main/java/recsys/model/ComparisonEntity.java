package recsys.model;

import javax.persistence.OneToOne;

/**
 * An entity that represents the results from transactionService.
 *
 * @author Trine Staverløkk
 * @version 0.1
 */
public class ComparisonEntity {

    @OneToOne
    private BankTransactionEntity bankTransactionEntity;

    @OneToOne
    private AccountingTransactionEntity accountingTransactionEntity;

    private Result result;

    private Status status;

    /**
     * No-Arg constructor for objects of BankTransactionEntity type.
     */
    public ComparisonEntity() {
        // Entities must have an empty constructor to allow dynamic class inspection and instantiation at runtime.
        // Without it, the run will be stopped by an exception
    }


    /**
     * Sets bank transaction entity
     *
     * @param bankTransactionEntity
     */
    public void setBankTransactionEntity(BankTransactionEntity bankTransactionEntity) {
        this.bankTransactionEntity = bankTransactionEntity;
    }

    /**
     * Sets accountingTransactionEntity
     *
     * @param accountingTransactionEntity the accountingTransactionEntity.
     */
    public void setAccountingTransactionEntity(AccountingTransactionEntity accountingTransactionEntity) {
        this.accountingTransactionEntity = accountingTransactionEntity;
    }

    /**
     * Sets result.
     *
     * @param result
     */
    public void setResult(Result result) {
        this.result = result;
    }

    /**
     * Sets status.
     *
     * @param status Status.
     */
    public void setStatus(Status status) {
        this.status = status;
    }


    /**
     * Gets the bankTransactionEntity.
     *
     * @return Returns the bankTransactionEntity.
     */
    public BankTransactionEntity getBankTransactionEntity() {
        return bankTransactionEntity;
    }


    /**
     * Gets the accountingTransactionEntity.
     *
     * @return Returns the accountingTransactionEntity.
     */
    public AccountingTransactionEntity getAccountingTransactionEntity() {
        return accountingTransactionEntity;
    }

    /**
     * Gets the result.
     *
     * @return Returns the result.
     */
    public Result getResult() {
        return result;
    }

    /**
     * Gets the status.
     *
     * @return Returns the status.
     */
    public Status getStatus() {
        return status;
    }
}
