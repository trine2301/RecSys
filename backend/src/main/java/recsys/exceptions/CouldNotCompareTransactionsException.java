package recsys.exceptions;

/**
 * Exception to be thrown when comparison  between transactions could not be made
 *
 * @author Trine Staverløkk
 * @version 0.1
 */
///Extends Throwable?
public class CouldNotCompareTransactionsException extends Exception {

    /**
     * Makes an instance of the CouldNotCompareTransactionsException class.
     * @param message the error message
     */
    public CouldNotCompareTransactionsException(String message) {
        super(message);
    }

}
