package me.jae57.woodywoody.exception;

public class FamilyNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FamilyNotFoundException() {
    }

    public FamilyNotFoundException(String message) {
        super(message);
    }
}
