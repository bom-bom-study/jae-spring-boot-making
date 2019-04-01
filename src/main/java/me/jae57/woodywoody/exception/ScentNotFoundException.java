package me.jae57.woodywoody.exception;

public class ScentNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ScentNotFoundException() { }
    public ScentNotFoundException(String message) {
        super(message);
    }
}
