package recsys.model;

/**
 * Entity to store the differences (results) from transactionService.
 * Can be expanded to find where the difference lies, and not only the
 */
public class DifferenceEntity {
    private double amountDifference;

    public double getAmountDifference() {
        return amountDifference;
    }

    public void setAmountDifference(double amountDifference) {
        this.amountDifference = amountDifference;
    }
}
