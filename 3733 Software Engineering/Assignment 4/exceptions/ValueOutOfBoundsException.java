package converter.exceptions;

/**
 * An Exception that is thrown when the integer has a value that cannot be represented
 * by the Elbonian Numerals
 *
 * @version 1/31/2019
 */
public class ValueOutOfBoundsException extends Exception {

    /**
     * Constructor with a descriptive message for the value out of bounds exception.
     *
     * @param message A description of the error
     */
    public ValueOutOfBoundsException(String message) {
        super(message);
    }

}
