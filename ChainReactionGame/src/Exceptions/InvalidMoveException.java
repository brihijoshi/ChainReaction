package Exceptions;

/**
 * Exception generated in case of an invalid move
 *
 * @author adsrc
 * @version 1.0
 */
public class InvalidMoveException extends Exception {

    /**
     * Constructor
     * @param message message to be displayed
     */
    public InvalidMoveException(String message) {
        super(message);
    }

}
