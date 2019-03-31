package me.jae57.woodywoody.exception;

public class EmptyDataException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmptyDataException(){
    }

    public EmptyDataException(String message) {
        super(message);
    }
}
