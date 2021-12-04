package project.model.exceptions;

public abstract class NamedException extends Exception {
    private final String FULL_MESSAGE;

    public NamedException(String _msg) {
        FULL_MESSAGE = _msg;
    }

    public String getFullMessage() {
        return FULL_MESSAGE;
    }
}
