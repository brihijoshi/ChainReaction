package Exceptions;

/**
 * Exception generated in case of an unexpected exit
 */
public class UnexpectedExitException extends Exception {

    /**
     * Constructor
     * @param message message to be displayed
     */
    public UnexpectedExitException(String message) {
        super(message);
    }

}
