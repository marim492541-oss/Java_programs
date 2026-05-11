package com.jspiders.exceptions;

public class DuplicateMovieIdException extends RuntimeException {
    public DuplicateMovieIdException(String message) {
        super(message);
    }
}
