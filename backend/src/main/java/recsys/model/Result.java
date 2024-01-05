package recsys.model;

/**
 * Represents the state of the comparison-process.
 *
 * @author Trine Staverløkk
 * @version 0.1
 */
public enum Result {

    //Bank and accounting transactions are matches.
    MATCH,

    //Bank and accounting transaction have same date but different amount.
    PARTIAL_MATCH,

    //Accounting transaction cannot find a correspondent bank transaction.
    MISSING_BANK_TRANS,

    //Bank transaction cannot find a correspondent accounting transaction.
    MISSING_ACC_TRANS
}
