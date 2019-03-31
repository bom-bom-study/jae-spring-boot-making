package me.jae57.woodywoody.exception;

public class DuplicateScentIdException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DuplicateScentIdException(){
    }

    public DuplicateScentIdException(String message) {
        super(message);
    }
}
