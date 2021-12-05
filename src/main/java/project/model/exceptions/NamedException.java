package project.model.exceptions;

import project.model.Utils;

/**
 * A custom exception for better output and specific exception handling.
 *
 * @author Elazar Fine https://github.com/Elfein7Night
 */
public abstract class NamedException extends Exception {
    private final String FULL_MESSAGE;

    public NamedException(String _msg) {
        FULL_MESSAGE = _msg;
    }

    public NamedException(Throwable throwable) {
        FULL_MESSAGE = throwable + " (" + Utils.getRootCause(throwable) + ")";
    }

    public String getFullMessage() {
        return FULL_MESSAGE;
    }
}
