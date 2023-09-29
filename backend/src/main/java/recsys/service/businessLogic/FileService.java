package recsys.service.businessLogic;

import groovy.lang.Singleton;

/**
 * Represents a comparison model. Contains logic to compare two files etc.
 */
@Singleton
public class FileService {

    /**
     * TODO finish logic.
     * Checks if files are the same or not.
     * @return true if files are similiar.
     */
    public boolean compareFiles() {
        return true;
    }



    /**
     * Checks if the input number is above zero.
     * @param numberToCheck the number to check.
     */
    private void checkIfNumberIsAboveZero(long numberToCheck, String prefix){
        if (numberToCheck <= 0){
            throw new IllegalArgumentException("The " + prefix + " must be larger than zero.");
        }
    }

    /**
     * Checks if an object is null.
     * @param object the object you want to check.
     * @param error  the error message the exception should have.
     * @throws IllegalArgumentException gets thrown if the object is null.
     */
    private void checkIfObjectIsNull(Object object, String error) {
        if (object == null) {
            throw new IllegalArgumentException("The " + error + " cannot be null.");
        }
    }

}


