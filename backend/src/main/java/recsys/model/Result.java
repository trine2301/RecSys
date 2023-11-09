package recsys.model;

/**
 * Represents the state of the comparison-process
 */
public enum Result {

    MATCH,

    // Its partial when the system finds match, but date or amount is not correct.
    PARTIAL_MATCH,

    MISSING_BANK_TRANS,

    MISSING_ACC_TRANS
}
