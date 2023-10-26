package recsys.model;

/**
 * Represents the state of the comparison-process
 */
public enum ComparisonState {

    MATCH,

    PARTIAL_MATCH,

    MISSING_BANK_TRANS,

    MISSING_ACC_TRANS
}
