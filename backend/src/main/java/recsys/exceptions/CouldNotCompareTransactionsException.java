package recsys.exceptions;

import java.io.Serializable;

public class CouldNotCompareTransactionsException extends Exception implements Serializable {

    /**
     * Makes an instance of the CouldNotCompareTransactionsException class.
     * @param message the error message
     */
    public CouldNotCompareTransactionsException(String message) {
        super(message);
    }
}
