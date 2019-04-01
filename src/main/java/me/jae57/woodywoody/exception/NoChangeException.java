package me.jae57.woodywoody.exception;

public class NoChangeException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public NoChangeException(String message) {
        super(message);
    }
}
